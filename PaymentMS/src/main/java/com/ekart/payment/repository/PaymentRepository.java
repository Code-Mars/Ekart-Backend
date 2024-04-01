package com.ekart.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ekart.payment.entity.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Long> {
    
}
