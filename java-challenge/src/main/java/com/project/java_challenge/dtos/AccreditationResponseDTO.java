package com.project.java_challenge.dtos;

/**
 import java.time.LocalDate;
public class AccreditationResponseDTO {

    private Long amount;
    private int id;
    private LocalDate date;
    private String pointOfSaleName;

    public AccreditationResponseDTO(Long amount, int id, LocalDate date, String pointOfSaleName) {
        this.amount = amount;
        this.id = id;
        this.date = date;
        this.pointOfSaleName = pointOfSaleName;
    }

    public AccreditationResponseDTO(AccreditationDTO accreditationDTO) {
    }
}
 */
