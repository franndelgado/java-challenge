package com.project.java_challenge.dtos;

public class AccreditationDTO {
    private int pointOfSaleId;
    private Long amount;

    public int getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}