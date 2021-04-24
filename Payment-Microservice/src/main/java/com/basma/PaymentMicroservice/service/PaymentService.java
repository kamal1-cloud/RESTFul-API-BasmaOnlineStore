package com.basma.PaymentMicroservice.service;

import com.basma.PaymentMicroservice.model.Payment;
import com.basma.PaymentMicroservice.repository.PaymentRepository;

import java.util.List;

public class PaymentService {

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> listAll(){
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public Payment getById(Long paymentId){
        return paymentRepository.findById(paymentId).get();
    }

    public void delete(Long paymentId){
        paymentRepository.deleteById(paymentId);
    }
}
