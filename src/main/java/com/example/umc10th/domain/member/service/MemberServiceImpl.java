package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.exception.RegionException;
import com.example.umc10th.domain.region.exception.code.RegionErrorCode;
import com.example.umc10th.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public SignUpResDTO signUp(SignUpReqDTO request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        Member member = MemberConverter.toMember(request, region);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResDTO(savedMember);
    }
}
