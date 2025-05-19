package com.project.java_challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AccreditationResponseDTO {

    private Integer idPointOfSale;
    private Double amount;
    private LocalDateTime receptionDate;
    private String pointOfSaleName;
}
