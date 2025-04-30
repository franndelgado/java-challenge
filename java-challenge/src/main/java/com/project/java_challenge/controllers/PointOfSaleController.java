package com.project.java_challenge.controllers;

import com.project.java_challenge.api.PointOfSaleApi;
import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.services.PointOfSaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point-of-sale")
public class PointOfSaleController implements PointOfSaleApi {

    private final PointOfSaleService pointOfSaleService;

    public PointOfSaleController(PointOfSaleService pointOfSaleService) {
        this.pointOfSaleService = pointOfSaleService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PointOfSale>> getAllPointOfSale(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(pointOfSaleService.getAllPointOfSale(page, size));
    }

    @Override
    @PostMapping
    public ResponseEntity<PointOfSale> addPointOfSale(@Valid @RequestBody PointOfSaleDTO pointOfSaleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pointOfSaleService.createNewPointOfSale(pointOfSaleDTO));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PointOfSale> updatePointOfSale(@PathVariable int id, @RequestBody String name) throws PointOfSaleNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(pointOfSaleService.updatePointOfSale(id, name));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointOfSale(@PathVariable int id) {
        try{
            pointOfSaleService.deletePointOfSale(id);
            return ResponseEntity.ok().build();
        } catch (PointOfSaleNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}