package com.example.umc10th.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;

public record MissionListReqDTO(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId
) {
}
