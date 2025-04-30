package com.project.java_challenge.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Accreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pointOfSaleId;

    private Long amount;

    private String pointOfSaleName;

    private LocalDate receptionDate;

    public Accreditation(Long amount, int pointOfSaleId, String pointOfSaleName, LocalDate receptionDate) {
        this.amount = amount;
        this.pointOfSaleId = pointOfSaleId;
        this.pointOfSaleName = pointOfSaleName;
        this.receptionDate = receptionDate;
    }

    public Accreditation() {

    }
}