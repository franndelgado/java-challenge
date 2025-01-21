package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointOfSaleService {

    private final List<PointOfSale> pointOfSaleList = new ArrayList<>();

    public PointOfSaleService() {
        pointOfSaleList.add(new PointOfSale(1, "CABA"));
        pointOfSaleList.add(new PointOfSale(2, "GBA_1"));
        pointOfSaleList.add(new PointOfSale(3, "GBA_2"));
        pointOfSaleList.add(new PointOfSale(4, "Santa Fe"));
        pointOfSaleList.add(new PointOfSale(5, "Córdoba"));
        pointOfSaleList.add(new PointOfSale(6, "Misiones"));
        pointOfSaleList.add(new PointOfSale(7, "Salta"));
        pointOfSaleList.add(new PointOfSale(8, "Chubut"));
        pointOfSaleList.add(new PointOfSale(9, "Santa Cruz"));
        pointOfSaleList.add(new PointOfSale(10, "Catamarca"));
    }

    public List<PointOfSale> getPointOfSale() {
        return pointOfSaleList;
    }

    public void createNewPointOfSale(PointOfSale pointOfSale) {
        pointOfSaleList.add(pointOfSale);
    }

    public void updatePointOfSale(PointOfSale pointOfSale) {
        for(PointOfSale pos : pointOfSaleList) {
            if(pos.getId() == pointOfSale.getId()) {
                pos.setName(pointOfSale.getName());
            }
        }
    }

    public void deletePointOfSale(int pointOfSaleId) {
        pointOfSaleList.removeIf(pointOfSale -> pointOfSale.getId() == pointOfSaleId);
    }
}
