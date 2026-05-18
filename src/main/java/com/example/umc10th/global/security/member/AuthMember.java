package com.example.umc10th.global.security.member;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.MemberAuth;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;
    private final MemberAuth memberAuth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return memberAuth.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
