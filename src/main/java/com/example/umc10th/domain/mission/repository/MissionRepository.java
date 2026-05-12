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
            where s.region.id = (
                select mem.region.id
                from Member mem
                where mem.id = :memberId
            )
              and m.endedAt >= current_date
              and not exists (
                  select 1
                  from MemberMission mm
                  where mm.member.id = :memberId
                    and mm.mission.id = m.id
              )
              and (:cursor is null or m.id < :cursor)
            order by m.id desc
            """)
    List<Mission> findAvailableMissionsByMemberRegion(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
