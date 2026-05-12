package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.ProviderType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "member_auth",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"provider_type", "provider_uid"})
        }
)
public class MemberAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_auth_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", length = 20, nullable = false)
    private ProviderType providerType;

    @Column(name = "provider_uid", length = 255, nullable = false)
    private String providerUid;

    @Column(name = "password", length = 255)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static MemberAuth createLocalAuth(String email, String password) {
        MemberAuth memberAuth = new MemberAuth();
        memberAuth.providerType = ProviderType.LOCAL;
        memberAuth.providerUid = email;
        memberAuth.password = password;
        return memberAuth;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
