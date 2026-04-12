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
            "리뷰를 성공적으로 작성했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
