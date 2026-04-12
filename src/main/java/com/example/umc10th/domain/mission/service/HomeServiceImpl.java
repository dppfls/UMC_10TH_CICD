package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.response.HomeResDTO;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Override
    public HomeResDTO getHome(Long cursor) {
        // 나중에 구현
        return MissionConverter.toHomeResDTO(
                null,
                null,
                null,
                null,
                null
        );
    }
}
