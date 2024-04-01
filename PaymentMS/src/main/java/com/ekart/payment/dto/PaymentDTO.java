package com.ekart.payment.dto;

import com.ekart.payment.entity.Payment;

public class PaymentDTO {
    private Long id;
    private Long userId;
    private PaymentType type;
    private Card card;
    private String upiId;
    private Integer amount;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public static PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUserId());
        dto.setType(payment.getType());
        dto.setCard(payment.getCard());
        dto.setUpiId(payment.getUpiId());
        dto.setAmount(payment.getAmount());
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        Payment entity = new Payment();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setType(dto.getType());
        entity.setCard(dto.getCard());
        entity.setUpiId(dto.getUpiId());
        entity.setAmount(dto.getAmount());
        return entity;
    }
    
}
