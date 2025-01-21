package com.project.java_challenge.controllers;

import com.project.java_challenge.dtos.PointOfSaleCost;
import com.project.java_challenge.services.CostService;
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
    public ResponseEntity<String> addNewCost(@RequestBody PointOfSaleCost costBody){
        costService.createNewPointOfSaleCost(costBody);
        return ResponseEntity.ok("New cost added.");
    }

    @DeleteMapping
    public String deleteCost(@RequestParam int idA, @RequestParam int idB){
        return costService.deletePointOfSaleCost(idA, idB);
    }

    /**
    @GetMapping("/{idA}")
    public List<PointOfSaleCost> getCostById(@PathVariable int idA){
        return costService.searchPointOfSaleCosts(idA);
    }
     */

    @GetMapping("/search/{id}")
    public String newSearchPointOfSaleCost(@PathVariable int id){
        return costService.newSearchPointOfSaleCost(id);
    }

    /**
    @GetMapping("/testing")
    public List<PointOfSaleCost> testing(@RequestParam int idA, @RequestParam int idB){
        return costService.getList(idA, idB);
    }
    */
}
