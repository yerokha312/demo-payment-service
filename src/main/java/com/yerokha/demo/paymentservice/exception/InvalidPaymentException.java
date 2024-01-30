package com.yerokha.demo.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidPaymentException extends RuntimeException {
    public InvalidPaymentException(String message) {
    }
}
