package com.project.java_challenge.dtos;

public class PointOfSale {

    private final int id;
    private String name;

    public PointOfSale(int id, String name) {
        this.id = id;
        this.name = name;
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
