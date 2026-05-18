package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select r
            from Review r
            join fetch r.store s
            where r.member.id = :memberId
              and (:cursor is null or r.id < :cursor)
            order by r.id desc
            """)
    List<Review> findMyReviewsOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            select r
            from Review r
            join fetch r.store s
            where r.member.id = :memberId
              and (
                    :starCursor is null
                    or r.star < :starCursor
                    or (r.star = :starCursor and r.id < :cursor)
              )
            order by r.star desc, r.id desc
            """)
    List<Review> findMyReviewsOrderByStarDesc(
            @Param("memberId") Long memberId,
            @Param("starCursor") BigDecimal starCursor,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
