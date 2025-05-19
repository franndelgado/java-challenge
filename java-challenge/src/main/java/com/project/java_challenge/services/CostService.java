package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.models.PointOfSaleCost;

import java.util.List;

public interface CostService {
    List<PointOfSaleCost> getCostsList();
    PointOfSaleCost createNewPointOfSaleCost(PointOfSaleCostDTO pointOfSaleCostDTO);
    void deletePointOfSaleCost(int idA, int idB);
    String searchPointOfSaleCost(Integer id);
}
