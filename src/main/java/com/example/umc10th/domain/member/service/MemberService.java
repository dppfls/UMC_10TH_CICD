package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.SignUpResDTO;
import com.example.umc10th.domain.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public SignUpResDTO signUp(SignUpReqDTO request) {

        // 임시 Member 객체 생성
        Member member = Member.createTempMember(1L);

        return MemberConverter.toSignUpResponse(member);
    }
}
