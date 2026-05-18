package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
        select mm
        from MemberMission mm
        join fetch mm.mission m
        join fetch m.store s
        where mm.member.id = :memberId
          and mm.isComplete = true
        order by mm.id desc
        """)
    Slice<MemberMission> findCompletedMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    @Query("""
        select mm
        from MemberMission mm
        join fetch mm.mission m
        join fetch m.store s
        where mm.member.id = :memberId
          and mm.isComplete = false
          and m.endedAt >= current_date
        order by mm.id desc
        """)
    Slice<MemberMission> findInProgressMissions(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    @Query("""
        select count(mm)
        from MemberMission mm
        where mm.member.id = :memberId
          and mm.isComplete = true
        """)
    Integer countCompletedMissionsByMemberId(
            @Param("memberId") Long memberId
    );
}