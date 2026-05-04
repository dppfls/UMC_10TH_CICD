package com.example.umc10th.domain.mission.controller;

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
    @GetMapping
    public ApiResponse<MissionListResDTO> getMissions(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(required = false) Long cursor
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_FOUND,
                missionService.getMissions(memberId, status, cursor)
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
