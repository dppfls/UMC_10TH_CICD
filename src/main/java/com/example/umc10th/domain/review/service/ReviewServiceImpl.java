package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Override
    public ReviewCreateResDTO createReview(Long storeId, ReviewCreateReqDTO request) {
        // 나중에 구현
        return ReviewConverter.toReviewCreateResDTO(
                null,
                storeId,
                request.star(),
                request.content(),
                request.imageUrls()
        );
    }
}
