package com.basma.PaymentMicroservice.repository;

import com.basma.PaymentMicroservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
