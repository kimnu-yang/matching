package com.matching.domain.domains.payment.service;

import com.matching.domain.domains.payment.repository.PaymentRepository;
import com.matching.storage.database.model.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment findByIdx(Long idx) {
        return paymentRepository.findById(idx).orElse(null);
    }

    public List<Payment> findAll(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return paymentRepository.findAll(pageable).getContent();
    }

    public Payment save(Payment payment) { return paymentRepository.save(payment); }
}
