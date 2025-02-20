package com.project.java_challenge.entities;

import jakarta.persistence.*;

@Entity
public class PointOfSaleCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer idA;
    private Integer idB;
    private Integer cost;

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public Integer getIdB() {
        return idB;
    }

    public void setIdB(Integer idB) {
        this.idB = idB;
    }
}
