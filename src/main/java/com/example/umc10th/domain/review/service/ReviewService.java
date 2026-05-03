package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.request.ReviewCreateReqDTO;
import com.example.umc10th.domain.review.dto.response.ReviewCreateResDTO;

public interface ReviewService {
    ReviewCreateResDTO createReview(Long storeId, ReviewCreateReqDTO request);
}
