package com.project.java_challenge.controllers;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.models.PointOfSale;
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
    public ResponseEntity<PointOfSale> addPointOfSale(@RequestBody PointOfSaleDTO pointOfSaleDTO) {
        return ResponseEntity.ok(pointOfSaleService.createNewPointOfSale(pointOfSaleDTO));
    }

    @PutMapping
    public ResponseEntity<PointOfSale> updatePointOfSale(@RequestBody PointOfSaleDTO pointOfSaleDTO) {
        return ResponseEntity.ok(pointOfSaleService.updatePointOfSale(pointOfSaleDTO));
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
