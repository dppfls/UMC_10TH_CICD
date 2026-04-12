package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.SignUpResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {
    private MemberConverter() {}

    public static SignUpResDTO toSignUpResponse(Member member) {
        return new SignUpResDTO(
                member.getId()
        );
    }
}
