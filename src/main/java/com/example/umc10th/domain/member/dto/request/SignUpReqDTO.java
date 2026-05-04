package com.example.umc10th.domain.member.dto.request;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SignUpReqDTO (

        @NotBlank(message = "닉네임은 필수입니다.")
        String nickname,

        @NotNull(message = "성별은 필수입니다.")
        Gender gender,

        @NotNull(message = "생년월일은 필수입니다.")
        LocalDate birth,

        @NotBlank(message = "주소는 필수입니다.")
        String address,

        @NotBlank(message = "전화번호는 필수입니다.")
        String phone,

        @NotNull(message = "전화번호 인증 여부는 필수입니다.")
        Boolean phoneVerified,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,

        @NotNull(message = "지역 ID는 필수입니다.")
        Long regionId
) {
}
