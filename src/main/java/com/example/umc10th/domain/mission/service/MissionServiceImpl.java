package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.request.MissionListReqDTO;
import com.example.umc10th.domain.mission.dto.request.MissionStatusUpdateReqDTO;
import com.example.umc10th.domain.mission.dto.response.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.response.MissionStatusUpdateResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.eums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.umc10th.domain.mission.eums.MissionStatus.COMPLETED;
import static com.example.umc10th.domain.mission.eums.MissionStatus.IN_PROGRESS;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private static final int PAGE_SIZE = 10;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionListResDTO getMissions(
            MissionListReqDTO request,
            MissionStatus status,
            Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);

        Slice<MemberMission> memberMissionSlice  = switch (status) {
            case IN_PROGRESS -> memberMissionRepository.findInProgressMissions(
                    request.memberId(),
                    pageRequest
            );
            case COMPLETED -> memberMissionRepository.findCompletedMissions(
                    request.memberId(),
                    pageRequest
            );
        };

        List<MemberMission> memberMissions = memberMissionSlice.getContent();

        List<MissionListResDTO.MissionPreview> missions = memberMissions.stream()
                .map(MissionConverter::toMissionPreview)
                .toList();

        return MissionConverter.toMissionListResDTO(
                missions,
                page,
                memberMissionSlice.hasNext()
        );
    }

    @Override
    @Transactional
    public MissionStatusUpdateResDTO updateMissionStatus(
            Long missionId,
            MissionStatusUpdateReqDTO request
    ) {
        return MissionConverter.toMissionStatusUpdateResDTO(
                missionId,
                request.status()
        );
    }
}