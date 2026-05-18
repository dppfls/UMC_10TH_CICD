package com.example.umc10th.domain.review.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record MyReviewListResDTO(
        List<MyReviewPreview> reviews,
        Long nextCursor,
        BigDecimal nextStarCursor,
        Boolean hasNext
) {
    public record MyReviewPreview(
            Long reviewId,
            String storeName,
            BigDecimal star,
            String content,
            String createdAt
    ) {
    }
}
