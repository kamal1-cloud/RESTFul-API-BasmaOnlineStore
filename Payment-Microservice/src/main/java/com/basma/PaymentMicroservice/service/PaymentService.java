package com.basma.PaymentMicroservice.service;

import com.basma.PaymentMicroservice.model.Payment;
import com.basma.PaymentMicroservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> listAll(){
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getById(Long paymentId){
        return paymentRepository.findById(paymentId);
    }

    public void delete(Long paymentId){
        paymentRepository.deleteById(paymentId);
    }

    public String paymentProcessing(){
        return new Random().nextBoolean()?"success":"failure";
    }

}
