package com.project.java_challenge.exceptions;

public class PointOfSaleNotFoundException extends Exception {

    public PointOfSaleNotFoundException(int id){
        super(String.format("The id %d does not exist.", id));
    }
}
