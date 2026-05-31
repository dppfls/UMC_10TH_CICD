package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.MemberAuth;
import com.example.umc10th.domain.member.enums.ProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberAuthRepository extends JpaRepository<MemberAuth, Long> {

    Optional<MemberAuth> findByProviderTypeAndProviderUid(
            ProviderType providerType,
            String providerUid
    );

    @Query("""
            select ma
            from MemberAuth ma
            join fetch ma.member m
            where ma.providerType = :providerType
              and ma.providerUid = :providerUid
            """)
    Optional<MemberAuth> findByProviderTypeAndProviderUidWithMember(
            @Param("providerType") ProviderType providerType,
            @Param("providerUid") String providerUid
    );
}
