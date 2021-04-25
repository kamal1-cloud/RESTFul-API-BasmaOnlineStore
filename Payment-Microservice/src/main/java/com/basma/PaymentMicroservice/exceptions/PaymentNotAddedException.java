package com.basma.PaymentMicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentNotAddedException extends RuntimeException {
    public PaymentNotAddedException(String s) {
        super(s);
    }
}
