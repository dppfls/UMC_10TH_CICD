package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.request.LoginReqDTO;
import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.LoginResDTO;
import com.example.umc10th.domain.member.dto.response.MyPageResDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;
import com.example.umc10th.global.security.member.AuthMember;

public interface MemberService {
    SignUpResDTO signUp(SignUpReqDTO request);
    LoginResDTO login(LoginReqDTO request);
    MyPageResDTO getMyPage(AuthMember member);
}
