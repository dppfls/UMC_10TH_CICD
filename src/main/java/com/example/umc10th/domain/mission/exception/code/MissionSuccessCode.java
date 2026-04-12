package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    HOME_INFO_FOUND(HttpStatus.OK,
            "HOME_INFO_FOUND",
            "홈 화면 정보를 성공적으로 조회했습니다."),
    MISSION_LIST_FOUND(HttpStatus.OK,
            "MISSION_LIST_FOUND",
            "미션 목록을 성공적으로 조회했습니다."),
    MISSION_STATUS_UPDATED(HttpStatus.OK,
            "MISSION_STATUS_UPDATED",
            "미션 상태를 성공적으로 변경했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
