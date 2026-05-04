package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.response.MyPageResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
public class MyPageController {

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping
    public ApiResponse<MyPageResDTO> getMyPage(
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MYPAGE_FOUND,
                memberService.getMyPage(memberId)
        );
    }
}
