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

    /**
     * <b>GET Method:</b><br>
     * This method gets the List of point of sale and returns it.
     * @return List<PointOfSale>
     */
    public List<PointOfSale> getPointOfSale() {
        return pointOfSaleList;
    }

    /**
     * <b>POST Method:</b><br>
     * This method receives a PointOfSale and adds it to the point Of Sale List.
     * @param pointOfSale
     */
    public void createNewPointOfSale(PointOfSale pointOfSale) {
        if(pointOfSale == null) {
            throw new IllegalArgumentException("Point Of Sale cannot be null.");
        } if(pointOfSale.getId() <= 0){
            throw new IllegalArgumentException("Point Of Sale id cannot be less than 0.");
        }
        if(pointOfSale.getName() == null || pointOfSale.getName().isEmpty()){
            throw new IllegalArgumentException("Point Of Sale name cannot be empty.");
        }
        pointOfSaleList.add(pointOfSale);
    }

    /**
     * <b>PUT Method:</b><br>
     * This method receives a PointOfSale and updates it.
     * @param pointOfSale
     */
    public void updatePointOfSale(PointOfSale pointOfSale) {

        PointOfSale existingPoint = pointOfSaleList.stream()
                .filter(pos -> pos.getId() == pointOfSale.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Point Of Sale not found."));
        existingPoint.setName(pointOfSale.getName());
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives a point of sale identifier and removes it.
     * @param pointOfSaleId
     */
    public void deletePointOfSale(int pointOfSaleId) {
        boolean removed = pointOfSaleList.removeIf(pointOfSale -> pointOfSale.getId() == pointOfSaleId);
        if(!removed){
            throw new IllegalArgumentException("Point Of Sale does not exist.");
        }
    }
}
