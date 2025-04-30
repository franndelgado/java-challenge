package com.project.java_challenge.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class PointOfSaleCost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer idA;
    private Integer idB;
    private Integer cost;

    public PointOfSaleCost(Integer idA, Integer idB,Integer cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }
}
