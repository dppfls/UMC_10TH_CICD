package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.request.MissionListReqDTO;
import com.example.umc10th.domain.mission.dto.request.MissionStatusUpdateReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;
import com.example.umc10th.domain.mission.eums.MissionStatus;

public interface MissionService {
    MissionListResDTO getMissions(MissionListReqDTO request, MissionStatus status, Integer page);
    MissionStatusUpdateResDTO updateMissionStatus(Long missionId, MissionStatusUpdateReqDTO request);
}
