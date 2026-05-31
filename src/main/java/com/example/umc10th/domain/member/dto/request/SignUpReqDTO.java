package com.example.umc10th.domain.member.dto.request;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record SignUpReqDTO (

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 20, message = "닉네임은 최대 20자까지 가능합니다.")
        String nickname,

        @NotNull(message = "성별은 필수입니다.")
        Gender gender,

        @NotNull(message = "생년월일은 필수입니다.")
        LocalDate birth,

        @NotBlank(message = "주소는 필수입니다.")
        @Size(max = 500, message = "주소는 최대 500자까지 가능합니다.")
        String address,

        @NotBlank(message = "전화번호는 필수입니다.")
        @Pattern(
                regexp = "^010\\d{8}$",
                message = "전화번호는 010으로 시작하는 11자리 숫자여야 합니다."
        )
        String phone,

        @NotNull(message = "전화번호 인증 여부는 필수입니다.")
        Boolean phoneVerified,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        @Size(max = 255, message = "이메일은 최대 255자까지 가능합니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$",
                message = "비밀번호는 영문과 숫자를 포함한 8자 이상 20자 이하이어야 합니다."
        )
        String password,

        @NotNull(message = "지역 ID는 필수입니다.")
        Long regionId
) {
}
