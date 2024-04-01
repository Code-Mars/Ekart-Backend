package com.ekart.payment.service;

import com.ekart.payment.dto.PaymentDTO;
import com.ekart.payment.exception.EkartException;

public interface PaymentService {
    public long getNextSequenceId(String key) throws EkartException;
    public Long pay(PaymentDTO paymentDTO) throws EkartException;
}
