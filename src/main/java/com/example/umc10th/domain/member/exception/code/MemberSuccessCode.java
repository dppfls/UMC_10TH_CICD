package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_CREATED (HttpStatus.CREATED,
            "MEMBER_CREATED",
            "회원가입이 완료되었습니다."),
    MEMBER_FOUND(HttpStatus.OK,
            "MEMBER_FOUND",
            "성공적으로 유저를 조회했습니다."),
    MYPAGE_FOUND(HttpStatus.OK,
            "MYPAGE_FOUND",
            "성공적으로 마이페이지 정보를 조회했습니다."),
    LOGIN_SUCCESS(HttpStatus.OK,
            "LOGIN_SUCCESS",
            "로그인에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
