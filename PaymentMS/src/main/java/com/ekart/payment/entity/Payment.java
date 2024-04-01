package com.ekart.payment.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ekart.payment.dto.Card;
import com.ekart.payment.dto.PaymentType;

@Document(collection="payment")
public class Payment {
    @Id
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
    
}
