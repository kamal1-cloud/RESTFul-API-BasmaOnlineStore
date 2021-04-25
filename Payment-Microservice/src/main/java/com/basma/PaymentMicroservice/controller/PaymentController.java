package com.basma.PaymentMicroservice.controller;

import com.basma.PaymentMicroservice.exceptions.PaymentNotAddedException;
import com.basma.PaymentMicroservice.exceptions.PaymentNotFoundException;
import com.basma.PaymentMicroservice.model.Payment;
import com.basma.PaymentMicroservice.service.PaymentService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final Logger log = LoggerFactory.getLogger(Payment.class);

    private static final String ENTITY_NAME = "Payment";

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> listPayments() {
        log.debug("REST request to get all payments");

        List<Payment> listPayments = paymentService.listAll();
        return ResponseEntity.ok().body(listPayments);
    }

    @PostMapping("/newPayment")
    public ResponseEntity<Payment> createNewCategory(@RequestBody Payment payment) throws PaymentNotAddedException {
        log.debug("REST request to save payment: {}", payment);
        if(payment.getPaymentId() != null) {
            throw new PaymentNotAddedException("A new payment cannot be added ID " + ENTITY_NAME + " id already exists");
        }

        Payment result = paymentService.save(payment);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/editPayment")
    public ResponseEntity<Payment> updateCategory(@RequestBody Payment payment) throws PaymentNotFoundException {
        log.debug("REST request to update payment : {}", payment);
        if(payment.getPaymentId() == null) {
            throw new PaymentNotFoundException("Invalid id " + ENTITY_NAME + " or idnull");
        }
        Payment result = paymentService.save(payment);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getCategory(@PathVariable("paymentId") Long paymentId){
        log.debug("REST request to get payment : {}", paymentId);
        Optional<Payment> payment = paymentService.getById(paymentId);

        return ResponseUtil.wrapOrNotFound(payment);
    }

    @DeleteMapping("/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCategory(@PathVariable("paymentId") Long paymentId) {
        log.debug("REST request to delete payment : {}", paymentId);
        paymentService.delete(paymentId);
        return ResponseEntity.noContent().build();
    }

}
