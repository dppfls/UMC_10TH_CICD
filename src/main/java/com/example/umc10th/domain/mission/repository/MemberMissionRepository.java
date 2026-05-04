package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
        select mm
        from MemberMission mm
        join fetch mm.mission m
        join fetch m.store s
        where mm.member.id = :memberId
          and mm.isComplete = :isComplete
          and (:cursor is null or mm.id < :cursor)
        order by mm.id desc
    """)
    List<MemberMission> findMyMissionsByStatus(
            @Param("memberId") Long memberId,
            @Param("isComplete") Boolean isComplete,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
        select count(mm)
        from MemberMission mm
        where mm.member.id = :memberId
          and mm.isComplete = true
    """)
    Integer countCompletedMissions(@Param("memberId") Long memberId);
}