package com.matching.domain.domains.payment.repository;

import com.matching.storage.database.model.payment.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // 전체 조회
    Page<Payment> findAll(Pageable pageable);
}