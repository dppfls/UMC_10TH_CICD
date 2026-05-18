package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED,
            "REVIEW_CREATED",
            "리뷰를 성공적으로 작성했습니다."
    ),
    MY_REVIEW_LIST_FOUND(HttpStatus.OK,
            "MY_REVIEW_LIST_FOUND",
            "내가 작성한 리뷰 목록을 조회했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
