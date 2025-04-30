package com.project.java_challenge.dtos;

 import lombok.Getter;

 import java.time.LocalDate;

@Getter
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

}
