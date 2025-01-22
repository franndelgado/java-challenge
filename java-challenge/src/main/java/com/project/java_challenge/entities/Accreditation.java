package com.project.java_challenge.entities;

 import jakarta.persistence.*;

 import java.time.LocalDate;

@Entity
public class Accreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pointOfSaleId;

    private Long amount;

    private String pointOfSaleName;

    private LocalDate receptionDate;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPointOfSaleName() {
        return pointOfSaleName;
    }

    public void setPointOfSaleName(String pointOfSaleName) {
        this.pointOfSaleName = pointOfSaleName;
    }

    public LocalDate getDate() {
        return receptionDate;
    }

    public void setDate(LocalDate receptionDate) {
        this.receptionDate = receptionDate;
    }

    public int getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}