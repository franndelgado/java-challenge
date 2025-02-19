package com.project.java_challenge.controllers;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.services.CostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costs")
public class CostController {

    private final CostService costService;

    public CostController(CostService costService) {
        this.costService = costService;
    }

    @GetMapping
    public ResponseEntity<List<PointOfSaleCostDTO>> getAllCosts(){
        return ResponseEntity.ok(costService.getCostsList());
    }

    @PostMapping
    public ResponseEntity<String> addNewCost(@RequestBody PointOfSaleCostDTO costBody){
        try{
            costService.createNewPointOfSaleCost(costBody);
            return ResponseEntity.ok("Created new point of sale cost successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCost(@RequestParam int idA, @RequestParam int idB){
        String result = costService.deletePointOfSaleCost(idA, idB);
        if(result.startsWith("Cost between id")){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<String> newSearchPointOfSaleCost(@PathVariable int id){
        String result = costService.searchPointOfSaleCost(id);
        if(!result.isEmpty()){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cost found.");
        }
    }
}
