package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.math.BigDecimal;
import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {}

    public static Review toReview(
            ReviewCreateReqDTO request,
            Member member,
            Store store
    ) {
        return Review.createReview(
                request.star(),
                request.content(),
                member,
                store
        );
    }

    public static ReviewCreateResDTO toReviewCreateResDTO(Review review) {
        return new ReviewCreateResDTO(
                review.getId(),
                review.getStore().getId(),
                review.getStar(),
                review.getContent(),
                List.of()
        );
    }
}
