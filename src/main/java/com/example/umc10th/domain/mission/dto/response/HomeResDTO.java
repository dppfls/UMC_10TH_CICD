package com.example.umc10th.domain.mission.dto.response;

import java.util.List;

public record HomeResDTO(
        String regionName,
        Integer completedMissionCount,
        List<HomeMissionPreview> availableMissions,
        Long nextCursor,
        Boolean hasNext
) {
    public record HomeMissionPreview(
            Long missionId,
            String storeName,
            String condition,
            Integer point,
            String deadline
    ) {}
}