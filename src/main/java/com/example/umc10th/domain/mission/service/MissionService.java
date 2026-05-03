package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.request.MissionStatusUpdateReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;

public interface MissionService {
    MissionListResDTO getMissions(String status, Long cursor);

    MissionStatusUpdateResDTO updateMissionStatus(Long missionId, MissionStatusUpdateReqDTO request);
}
