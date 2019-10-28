package com.ecommerce.payment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PaymentNotPossibleException extends RuntimeException {


    public PaymentNotPossibleException(String message) {
        super(message);
    }
}
