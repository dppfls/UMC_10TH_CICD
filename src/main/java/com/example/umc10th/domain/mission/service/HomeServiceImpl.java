package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.response.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {

    private static final int PAGE_SIZE = 10;

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public HomeResDTO getHome(Long memberId, Long cursor) {

        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        String regionName = memberRepository.findRegionNameByMemberId(memberId);

        Integer completedMissionCount =
                memberMissionRepository.countCompletedMissionsByMemberId(memberId);

        PageRequest pageRequest = PageRequest.of(0, PAGE_SIZE + 1);

        List<Mission> missions = missionRepository.findAvailableMissionsByMemberRegion(
                memberId,
                cursor,
                pageRequest
        );

        boolean hasNext = missions.size() > PAGE_SIZE;

        if (hasNext) {
            missions = missions.subList(0, PAGE_SIZE);
        }

        Long nextCursor = hasNext
                ? missions.get(missions.size() - 1).getId()
                : null;

        List<HomeResDTO.HomeMissionPreview> availableMissions = missions.stream()
                .map(MissionConverter::toHomeMissionPreview)
                .toList();

        return MissionConverter.toHomeResDTO(
                regionName,
                completedMissionCount,
                availableMissions,
                nextCursor,
                hasNext
        );
    }
}
