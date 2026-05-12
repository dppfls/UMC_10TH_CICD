package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER_NOT_FOUND",
            "존재하지 않는 회원입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,
            "EMAIL_ALREADY_EXISTS",
            "이미 사용 중인 이메일입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
