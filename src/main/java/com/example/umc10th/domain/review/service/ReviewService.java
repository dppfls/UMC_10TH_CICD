package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.MyReviewListResDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;
import com.example.umc10th.domain.review.enums.ReviewSortType;

import java.math.BigDecimal;

public interface ReviewService {
    ReviewCreateResDTO createReview(Long storeId, Long memberId, ReviewCreateReqDTO request);
    MyReviewListResDTO getMyReviews(Long memberId, ReviewSortType sort, Long cursor, BigDecimal starCursor);
}
