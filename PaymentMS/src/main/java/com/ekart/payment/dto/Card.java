package com.ekart.payment.dto;

import java.time.LocalDate;

public class Card {
    private String number;
    private Integer cvv;
    private LocalDate expiryDate;
    private String nameOnCard;
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Integer getCvv() {
        return cvv;
    }
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getNameOnCard() {
        return nameOnCard;
    }
    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }
    
}
