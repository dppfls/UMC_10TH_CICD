package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SignUpReqDTO (

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String name,

        @NotNull
        Gender gender,

        @NotNull
        LocalDate birth,

        @NotBlank
        String address,

        @NotEmpty
        List<Long> preferredFoodIds,

        @Valid
        @NotEmpty
        List<TermConsentRequest> termsConsents

) {
    public record TermConsentRequest(
            @NotNull Long termId,
            @NotNull Boolean isAgreed
    ) {}
}
