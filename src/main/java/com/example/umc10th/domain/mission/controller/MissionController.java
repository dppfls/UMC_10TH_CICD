package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.request.MissionListReqDTO;
import com.example.umc10th.domain.mission.dto.request.MissionStatusUpdateReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;
import com.example.umc10th.domain.mission.eums.MissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회 (진행중, 진행 완료)
    // 오프셋 기반 페이지네이션
    @PostMapping("/{status}")
    public ApiResponse<MissionListResDTO> getMissions(
            @PathVariable MissionStatus status,
            @RequestBody @Valid MissionListReqDTO request,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                missionService.getMissions(request, status, page)
        );
    }

    // 미션 성공 누르기
    @PatchMapping("/{missionId}")
    public ApiResponse<MissionStatusUpdateResDTO> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionStatusUpdateReqDTO request
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_STATUS_UPDATED,
                missionService.updateMissionStatus(missionId, request)
        );
    }
}
