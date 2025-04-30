package com.project.java_challenge.controllers;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.models.PointOfSaleCost;
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
    public ResponseEntity<List<PointOfSaleCost>> getAllCosts(){
        return ResponseEntity.ok(costService.getCostsList());
    }

    @PostMapping
    public ResponseEntity<PointOfSaleCost> addNewCost(@RequestBody PointOfSaleCostDTO costBody){
        return ResponseEntity.ok(costService.createNewPointOfSaleCost(costBody));
    }


    @DeleteMapping
    public ResponseEntity<String> deleteCost(@RequestParam int idA, @RequestParam int idB){
        String result = costService.deletePointOfSaleCost(idA, idB);
        if(result.startsWith("Successfully deleted point Of Sale Cost.")){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<String> newSearchPointOfSaleCost(@PathVariable int id){
        return ResponseEntity.ok(costService.searchPointOfSaleCost(id));
    }
}
