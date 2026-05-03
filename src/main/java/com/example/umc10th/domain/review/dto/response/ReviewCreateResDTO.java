package com.example.umc10th.domain.review.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ReviewCreateResDTO(
        Long reviewId,
        Long storeId,
        BigDecimal star,
        String content,
        List<String> imageUrls
) {
}
