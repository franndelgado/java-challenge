package com.project.java_challenge.dtos;

public class PointOfSaleCost {

    private final int idA;
    private final int idB;
    private final int cost;

    public PointOfSaleCost(int idA, int idB, int cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

    public int getIdA() {
        return idA;
    }

    public int getIdB() {
        return idB;
    }

    public int getCost() {
        return cost;
    }

}
