package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.MyReviewListResDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {

    private final ReviewService reviewService;

    // 마이페이지 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewCreateResDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestBody @Valid ReviewCreateReqDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(storeId, memberId, request)
        );
    }

    // 내가 생성한 리뷰 조회
    // 커서 기반 페이지네이션
    @GetMapping("/my/reviews")
    public ApiResponse<MyReviewListResDTO> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam ReviewSortType sort,
            @RequestParam(required = false) Long cursor,
            @RequestParam(required = false) BigDecimal starCursor
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.MY_REVIEW_LIST_FOUND,
                reviewService.getMyReviews(memberId, sort, cursor, starCursor)
        );
    }
}
