package com.matching.storage.database.model.payment;

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
@Table(name = "payments")
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("결제 Index")
    private Long idx;

    @Column(name = "member_idx", nullable = false)
    @Comment("회원 Index")
    private Long memberIdx;

    @Column(name = "order_id", nullable = false)
    @Comment("주문 번호")
    private String orderId;

    @Column(name = "status", nullable = false)
    @Comment("주문 상태")
    private String status;

    @Column(name = "amount", nullable = false)
    @Comment("결제 금액")
    private Long amount;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Comment("생성일")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
