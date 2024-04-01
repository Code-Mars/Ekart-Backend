package com.ekart.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.payment.dto.PaymentDTO;
import com.ekart.payment.exception.EkartException;
import com.ekart.payment.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin
@Validated
@RequestMapping("/payment")
public class PaymentAPI {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Long> pay(@RequestBody @Valid PaymentDTO paymentDTO) throws EkartException{
        return new ResponseEntity<>(paymentService.pay(paymentDTO), HttpStatus.CREATED);
    }
}
