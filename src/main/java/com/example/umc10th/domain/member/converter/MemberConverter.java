package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.response.SignUpResDTO;

public class MemberConverter {

    private MemberConverter() {}

    public static SignUpResDTO toSignUpResDTO(Long memberId) {
        return new SignUpResDTO(memberId);
    }
}
