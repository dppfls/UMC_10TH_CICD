package com.example.umc10th.domain.member.entity;


import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;
}
