package com.example.umc10th.domain.member.dto.response;

public record MyPageResDTO(
        Long memberId,
        String nickname,
        String email,
        String phone,
        Boolean phoneVerified,
        Integer point
) {
}