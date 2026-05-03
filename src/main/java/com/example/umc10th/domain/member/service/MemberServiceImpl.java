package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public SignUpResDTO signUp(SignUpReqDTO request) {
        // 나중에 구현
        return MemberConverter.toSignUpResDTO(1L);
    }
}
