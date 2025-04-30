package com.project.java_challenge.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccreditationDTO {

    @NotBlank(message = "Point Of Sale Identifier cannot be null.")
    private Integer pointOfSaleId;
    @NotBlank(message = "Amount cannot be null.")
    private Long amount;
}