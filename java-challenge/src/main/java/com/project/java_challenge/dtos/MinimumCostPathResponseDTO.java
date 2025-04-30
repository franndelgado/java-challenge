package com.project.java_challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MinimumCostPathResponseDTO {
    private int totalCost;
    private List<String> path;
}
