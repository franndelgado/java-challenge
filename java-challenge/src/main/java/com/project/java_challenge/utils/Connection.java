package com.project.java_challenge.utils;

import lombok.Getter;

@Getter
public class Connection {
    private int destination;
    private int cost;

    public Connection(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }
}
