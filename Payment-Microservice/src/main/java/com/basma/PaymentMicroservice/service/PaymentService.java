package com.basma.PaymentMicroservice.service;

import com.basma.PaymentMicroservice.VO.Order;
import com.basma.PaymentMicroservice.VO.ResponseTemplateVO;
import com.basma.PaymentMicroservice.model.Payment;
import com.basma.PaymentMicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public ResponseTemplateVO getPaymentWithOrder(Long paymentId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Payment payment = paymentRepository.findByPaymentId(paymentId);

        Order order = restTemplate.getForObject("", Order.class);
    }

    public List<Payment> listAll(){
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public Optional<Payment> getById(Long paymentId){
        return paymentRepository.findById(paymentId);
    }

    public void delete(Long paymentId){
        paymentRepository.deleteById(paymentId);
    }
}
