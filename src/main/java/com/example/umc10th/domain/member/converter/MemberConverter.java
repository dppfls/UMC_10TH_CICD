package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.MemberAuth;
import com.example.umc10th.domain.region.entity.Region;

public class MemberConverter {

    private MemberConverter() {}

    public static Member toMember(SignUpReqDTO request, Region region) {
        Member member = Member.createMember(
                request.nickname(),
                request.gender(),
                request.birth(),
                request.address(),
                request.phone(),
                request.phoneVerified(),
                request.email(),
                region
        );

        MemberAuth memberAuth = MemberAuth.createLocalAuth(
                request.email(),
                request.password()
        );

        member.addMemberAuth(memberAuth);

        return member;
    }

    public static SignUpResDTO toSignUpResDTO(Member member) {
        return new SignUpResDTO(
                member.getId(),
                member.getNickname(),
                member.getEmail()
        );
    }
}
