package com.project.java_challenge.dtos;

 import java.time.LocalDate;

public class AccreditationResponseDTO {

    private int pointOfSaleId;
    private Long amount;
    private LocalDate date;
    private String pointOfSaleName;

    public AccreditationResponseDTO(int pointOfSaleId, Long amount, LocalDate date, String pointOfSaleName) {
        this.pointOfSaleId = pointOfSaleId;
        this.amount = amount;
        this.date = date;
        this.pointOfSaleName = pointOfSaleName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public String getPointOfSaleName() {
        return pointOfSaleName;
    }

    public void setPointOfSaleName(String pointOfSaleName) {
        this.pointOfSaleName = pointOfSaleName;
    }

    public AccreditationResponseDTO(AccreditationDTO accreditationDTO) {
    }
}
