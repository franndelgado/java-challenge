package com.project.java_challenge.dtos;

import lombok.Getter;

@Getter
public class PointOfSaleCostDTO {

    private Integer idA;
    private Integer idB;
    private Integer cost;

    public PointOfSaleCostDTO(Integer idA, Integer idB, Integer cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

}
