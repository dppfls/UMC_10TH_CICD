package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.MyReviewListResDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    @Transactional
    public ReviewCreateResDTO createReview(
            Long storeId,
            Long memberId,
            ReviewCreateReqDTO request
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);

        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewCreateResDTO(savedReview);
    }

    @Override
    public MyReviewListResDTO getMyReviews(
            Long memberId,
            ReviewSortType sort,
            Long cursor,
            BigDecimal starCursor
    ) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        PageRequest pageRequest = PageRequest.of(0, PAGE_SIZE);

        Slice<Review> reviewSlice = switch (sort) {
            case ID -> reviewRepository.findMyReviewsOrderByIdDesc(
                    memberId,
                    cursor,
                    pageRequest
            );
            case STAR -> reviewRepository.findMyReviewsOrderByStarDesc(
                    memberId,
                    starCursor,
                    cursor,
                    pageRequest
            );
        };

        List<Review> reviews = reviewSlice.getContent();

        Long nextCursor = reviewSlice.hasNext()
                ? reviews.get(reviews.size() - 1).getId()
                : null;

        BigDecimal nextStarCursor = reviewSlice.hasNext() && sort == ReviewSortType.STAR
                ? reviews.get(reviews.size() - 1).getStar()
                : null;

        List<MyReviewListResDTO.MyReviewPreview> reviewPreviews = reviews.stream()
                .map(ReviewConverter::toMyReviewPreview)
                .toList();

        return ReviewConverter.toMyReviewListResDTO(
                reviewPreviews,
                nextCursor,
                nextStarCursor,
                reviewSlice.hasNext()
        );
    }
}
