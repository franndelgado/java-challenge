package com.project.java_challenge.services;

import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.exceptions.MinimumCostPathException;

public interface PointOfSaleGraphService {
    MinimumCostPathResponseDTO getMinimumCostPath(int idA, int idB) throws MinimumCostPathException;
}
