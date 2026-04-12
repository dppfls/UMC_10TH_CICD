package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.SignUpResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<SignUpResDTO> signUp(
            @RequestBody @Valid SignUpReqDTO request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK, memberService.signUp(request)
        );
    }
}
