package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    // 마이페이지 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewCreateResDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewCreateReqDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(storeId, request)
        );
    }
}
