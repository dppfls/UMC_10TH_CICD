package com.example.umc10th.domain.mission.dto.response;

import java.util.List;

public record MissionListResDTO(
        List<MissionPreview> missions,
        Long nextCursor,
        Boolean hasNext
) {
    public record MissionPreview(
            Long missionId,
            String storeName,
            String condition,
            Integer point,
            String deadline,
            String status
    ) {}
}
