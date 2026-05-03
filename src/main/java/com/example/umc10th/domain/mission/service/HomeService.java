package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.response.HomeResDTO;

public interface HomeService {
    HomeResDTO getHome(Long cursor);
}
