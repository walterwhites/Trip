package com.ecommerce.payment.controller;

import com.ecommerce.payment.dao.PaymentDao;
import com.ecommerce.payment.model.Payment;
import com.ecommerce.payment.exceptions.PaymentAlreadyExistException;
import com.ecommerce.payment.exceptions.PaymentNotPossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    PaymentDao paymentDao;

    @PostMapping(value = "/payment")
    public ResponseEntity<Payment>  paidCommand(@RequestBody Payment payment){

        Payment paymentExist = paymentDao.findByIdCommand(payment.getIdCommand());
        if(paymentExist != null) throw new PaymentAlreadyExistException("This command is already paid");

        Payment newPayment = paymentDao.save(payment);

        if(newPayment == null) throw new PaymentNotPossibleException("Error, impossible to perform the payment, retry more late");

        //TODO Nous allons appeler le Microservice Commandes ici pour lui signifier que le payment est accepté

        return new ResponseEntity<Payment>(newPayment, HttpStatus.CREATED);

    }




}
