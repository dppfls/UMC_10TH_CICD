package com.example.umc10th.domain.review.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ReviewCreateReqDTO(

        @NotNull
        @DecimalMin(value = "0.0")
        @DecimalMax(value = "5.0")
        BigDecimal star,

        @NotBlank
        String content,

        List<String> imageUrls

) {
}
