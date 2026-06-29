package com.example.umc10th.global.security.member;

import com.example.umc10th.domain.member.entity.MemberAuth;
import com.example.umc10th.domain.member.enums.ProviderType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberAuthRepository memberAuthRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        MemberAuth memberAuth = memberAuthRepository
                .findByProviderTypeAndProviderUidWithMember(ProviderType.LOCAL, username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new AuthMember(memberAuth.getMember(), memberAuth);
    }
}
