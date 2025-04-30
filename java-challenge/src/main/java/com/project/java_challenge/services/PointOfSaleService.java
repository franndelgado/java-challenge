package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;

import java.util.List;

public interface PointOfSaleService {
    List<PointOfSale> getAllPointOfSale(int page, int size);
    PointOfSale createNewPointOfSale(PointOfSaleDTO pointOfSaleDTO);
    PointOfSale updatePointOfSale(int id, String name) throws PointOfSaleNotFoundException;
    void deletePointOfSale(int id) throws PointOfSaleNotFoundException;
}
