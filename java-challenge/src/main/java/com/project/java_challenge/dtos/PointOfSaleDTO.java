package com.project.java_challenge.dtos;

import jakarta.validation.constraints.NotBlank;

public class PointOfSaleDTO {

    private Integer id;
    @NotBlank(message = "The name of the point of sale cannot be empty.")
    private String name;

    public PointOfSaleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PointOfSaleDTO() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
