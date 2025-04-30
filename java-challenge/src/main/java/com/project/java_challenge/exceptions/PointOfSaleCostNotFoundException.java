package com.project.java_challenge.exceptions;

public class PointOfSaleCostNotFoundException extends RuntimeException{

    public PointOfSaleCostNotFoundException(int idA, int idB){
        super(String.format("There is no direct path between id %d and %d.", idA, idB));
    }
}
