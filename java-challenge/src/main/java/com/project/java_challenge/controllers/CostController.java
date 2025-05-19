package com.project.java_challenge.controllers;

import com.project.java_challenge.api.CostApi;
import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.exceptions.MinimumCostPathException;
import com.project.java_challenge.exceptions.PointOfSaleCostNotFoundException;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.services.CostService;
import com.project.java_challenge.services.PointOfSaleGraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/costs")
public class CostController implements CostApi {

    private final CostService costService;
    private final PointOfSaleGraphService pointOfSaleGraphService;

    @Override
    @GetMapping
    public ResponseEntity<List<PointOfSaleCost>> getAllCosts(){
        return ResponseEntity.ok(costService.getCostsList());
    }

    @Override
    @PostMapping
    public ResponseEntity<?> addNewCost(@RequestBody PointOfSaleCostDTO costBody){
        try{
            return ResponseEntity.ok(costService.createNewPointOfSaleCost(costBody));
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteCost(@RequestParam int idA, @RequestParam int idB){
        try{
            costService.deletePointOfSaleCost(idA, idB);
            return ResponseEntity.ok().build();
        } catch (PointOfSaleCostNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/search/{id}")
    public ResponseEntity<String> newSearchPointOfSaleCost(@PathVariable int id){
        return ResponseEntity.ok(costService.searchPointOfSaleCost(id));
    }

    @Override
    @GetMapping("/minimumPath")
    public ResponseEntity<MinimumCostPathResponseDTO> getMinimumPath(@RequestParam int from,
                                                                     @RequestParam int to) throws MinimumCostPathException {
        return ResponseEntity.ok(pointOfSaleGraphService.getMinimumCostPath(from, to));
    }
}
