package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.response.HomeResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {

    private final HomeService homeService;

    // 홈화면 조회
    @GetMapping
    public ApiResponse<HomeResDTO> getHome(
            @RequestParam(required = false) Long cursor
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.HOME_INFO_FOUND,
                homeService.getHome(cursor)
        );
    }
}
