package com.matching.storage.database.model.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 Index")
    private Long idx;

    @Column(name = "name", length = 20, nullable = false)
    @Comment("회원 이름")
    private String name;

    @Column(name = "hits", nullable = false)
    @Comment("프로필 조회수")
    private Long hits;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Comment("생성일")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
