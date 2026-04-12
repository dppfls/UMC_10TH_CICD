package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.request.MissionStatusUpdateReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;
import org.springframework.stereotype.Service;

@Service
public class MissionServiceImpl implements MissionService {

    @Override
    public MissionListResDTO getMissions(String status, Long cursor) {
        // 나중에 구현
        return MissionConverter.toMissionListResDTO(
                null,
                null,
                null
        );
    }

    @Override
    public MissionStatusUpdateResDTO updateMissionStatus(Long missionId, MissionStatusUpdateReqDTO request) {
        // 나중에 구현
        return MissionConverter.toMissionStatusUpdateResDTO(
                missionId,
                request.status()
        );
    }
}