package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.MemberAuth;
import com.example.umc10th.domain.member.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {

    Optional<MemberAuth> findByProviderTypeAndProviderUid(
            ProviderType providerType,
            String providerUid
    );
}
