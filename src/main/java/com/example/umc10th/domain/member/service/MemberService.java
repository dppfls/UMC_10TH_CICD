package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;

public interface MemberService {
    SignUpResDTO signUp(SignUpReqDTO request);
}
