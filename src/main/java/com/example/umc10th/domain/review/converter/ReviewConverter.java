package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;

import java.math.BigDecimal;
import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {}

    public static ReviewCreateResDTO toReviewCreateResDTO(
            Long reviewId,
            Long storeId,
            BigDecimal star,
            String content,
            List<String> imageUrls
    ) {
        return new ReviewCreateResDTO(
                reviewId,
                storeId,
                star,
                content,
                imageUrls
        );
    }
}
