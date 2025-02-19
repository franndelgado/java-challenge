package com.project.java_challenge.controllers;

import com.project.java_challenge.dtos.PointOfSale;
import com.project.java_challenge.services.PointOfSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point-of-sale")
public class PointOfSaleController {

    private final PointOfSaleService pointOfSaleService;

    public PointOfSaleController(PointOfSaleService pointOfSaleService) {
        this.pointOfSaleService = pointOfSaleService;
    }

    @GetMapping
    public ResponseEntity<List<PointOfSale>> getAllPointOfSale() {
        return ResponseEntity.ok(pointOfSaleService.getPointOfSale());
    }

    @PostMapping
    public ResponseEntity<String> addPointOfSale(@RequestBody PointOfSale pointOfSale) {
        try{
            pointOfSaleService.createNewPointOfSale(pointOfSale);
            return ResponseEntity.ok("Created new point of sale successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePointOfSale(@RequestBody PointOfSale pointOfSale) {
        if(pointOfSale == null) {
            return ResponseEntity.badRequest().body("Point Of Sale is null");
        }
        else {
        pointOfSaleService.updatePointOfSale(pointOfSale);
        return ResponseEntity.ok("Successfully Updated.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePointOfSale(@PathVariable int id) {
        pointOfSaleService.deletePointOfSale(id);
        return ResponseEntity.ok("Successfully Deleted.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
