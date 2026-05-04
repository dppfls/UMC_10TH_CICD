package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
        select m
        from Mission m
        join fetch m.store s
        join fetch s.region r
        where r.id = :regionId
          and (:cursor is null or m.id < :cursor)
          and m.id not in (
              select mm.mission.id
              from MemberMission mm
              where mm.member.id = :memberId
          )
        order by m.id desc
    """)
    List<Mission> findAvailableMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
