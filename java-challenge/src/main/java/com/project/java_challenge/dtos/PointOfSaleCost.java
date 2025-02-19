package com.project.java_challenge.dtos;

public class PointOfSaleCost {

    private final Integer idA;
    private final Integer idB;
    private final Integer cost;

    public PointOfSaleCost(Integer idA, Integer idB, Integer cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

    public Integer getIdA() {
        return idA;
    }

    public Integer getIdB() {
        return idB;
    }

    public Integer getCost() {
        return cost;
    }

}
