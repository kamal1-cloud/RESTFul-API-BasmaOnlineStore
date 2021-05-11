package com.basma.PaymentMicroservice.repository;

import com.basma.PaymentMicroservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentId(Long paymentId);

    Optional<Payment> findByOrderId(int orderId);
}
