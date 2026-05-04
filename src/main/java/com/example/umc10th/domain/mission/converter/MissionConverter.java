package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.response.HomeResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    private MissionConverter() {}

    public static HomeResDTO toHomeResDTO(
            String regionName,
            Integer completedMissionCount,
            List<HomeResDTO.HomeMissionPreview> availableMissions,
            Long nextCursor,
            Boolean hasNext
    ) {
        return new HomeResDTO(
                regionName,
                completedMissionCount,
                availableMissions,
                nextCursor,
                hasNext
        );
    }

    public static HomeResDTO.HomeMissionPreview toHomeMissionPreview(
            Long missionId,
            String storeName,
            String condition,
            Integer point,
            String deadline
    ) {
        return new HomeResDTO.HomeMissionPreview(
                missionId,
                storeName,
                condition,
                point,
                deadline
        );
    }

    public static MissionListResDTO toMissionListResDTO(
            List<MissionListResDTO.MissionPreview> missions,
            Long nextCursor,
            Boolean hasNext
    ) {
        return new MissionListResDTO(
                missions,
                nextCursor,
                hasNext
        );
    }

    public static MissionListResDTO.MissionPreview toMissionPreview(
            Long missionId,
            String storeName,
            String condition,
            Integer point,
            String deadline,
            String status
    ) {
        return new MissionListResDTO.MissionPreview(
                missionId,
                storeName,
                condition,
                point,
                deadline,
                status
        );
    }

    public static MissionStatusUpdateResDTO toMissionStatusUpdateResDTO(
            Long missionId,
            String status
    ) {
        return new MissionStatusUpdateResDTO(missionId, status);
    }

    public static MissionListResDTO.MissionPreview toMissionPreview(
            MemberMission memberMission
    ) {
        String status = memberMission.getIsComplete() ? "COMPLETED" : "IN_PROGRESS";

        return new MissionListResDTO.MissionPreview(
                memberMission.getMission().getId(),
                memberMission.getMission().getStore().getName(),
                memberMission.getMission().getCondition(),
                memberMission.getMission().getPoint(),
                memberMission.getMission().getEndedAt().toString(),
                status
        );
    }
}