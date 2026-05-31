package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.request.LoginReqDTO;
import com.example.umc10th.domain.member.dto.request.SignUpReqDTO;
import com.example.umc10th.domain.member.dto.response.LoginResDTO;
import com.example.umc10th.domain.member.dto.response.MyPageResDTO;
import com.example.umc10th.domain.member.dto.response.SignUpResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.MemberAuth;
import com.example.umc10th.domain.member.enums.ProviderType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberAuthRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.region.entity.Region;
import com.example.umc10th.domain.region.exception.RegionException;
import com.example.umc10th.domain.region.exception.code.RegionErrorCode;
import com.example.umc10th.domain.region.repository.RegionRepository;
import com.example.umc10th.global.security.member.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.umc10th.global.security.util.JwtUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberAuthRepository memberAuthRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public SignUpResDTO signUp(SignUpReqDTO request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = MemberConverter.toMember(request, region, encodedPassword);

        Member savedMember = memberRepository.save(member);

        return MemberConverter.toSignUpResDTO(savedMember);
    }

    @Override
    public MyPageResDTO getMyPage(AuthMember authMember) {

        return MemberConverter.toMyPageResDTO(authMember.getMember());
    }

    @Override
    public LoginResDTO login(LoginReqDTO request) {
        MemberAuth memberAuth = memberAuthRepository
                .findByProviderTypeAndProviderUid(ProviderType.LOCAL, request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.password(), memberAuth.getPassword())) {
            throw new MemberException(MemberErrorCode.LOGIN_FAILED);
        }

        AuthMember authMember = new AuthMember(memberAuth.getMember(), memberAuth);

        String accessToken = jwtUtil.createAccessToken(authMember);

        return new LoginResDTO(
                memberAuth.getMember().getId(),
                accessToken
        );
    }
}
