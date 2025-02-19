package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointOfSaleService {

    private final List<PointOfSaleDTO> pointOfSaleDTOList = new ArrayList<>();

    public PointOfSaleService() {
        pointOfSaleDTOList.add(new PointOfSaleDTO(1, "CABA"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(2, "GBA_1"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(3, "GBA_2"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(4, "Santa Fe"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(5, "Córdoba"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(6, "Misiones"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(7, "Salta"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(8, "Chubut"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(9, "Santa Cruz"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(10, "Catamarca"));
    }

    /**
     * <b>GET Method:</b><br>
     * This method gets the List of point of sale and returns it.
     * @return List<PointOfSale>
     */
    public List<PointOfSaleDTO> getPointOfSale() {
        return pointOfSaleDTOList;
    }

    /**
     * <b>POST Method:</b><br>
     * This method receives a PointOfSale and adds it to the point Of Sale List.
     * @param pointOfSaleDTO
     */
    public void createNewPointOfSale(PointOfSaleDTO pointOfSaleDTO) {
        if(pointOfSaleDTO == null) {
            throw new IllegalArgumentException("Point Of Sale cannot be null.");
        } if(pointOfSaleDTO.getId() <= 0){
            throw new IllegalArgumentException("Point Of Sale id cannot be less than 0.");
        }
        if(pointOfSaleDTO.getName() == null || pointOfSaleDTO.getName().isEmpty()){
            throw new IllegalArgumentException("Point Of Sale name cannot be empty.");
        }
        pointOfSaleDTOList.add(pointOfSaleDTO);
    }

    /**
     * <b>PUT Method:</b><br>
     * This method receives a PointOfSale and updates it.
     * @param pointOfSaleDTO
     */
    public void updatePointOfSale(PointOfSaleDTO pointOfSaleDTO) {

        PointOfSaleDTO existingPoint = pointOfSaleDTOList.stream()
                .filter(pos -> pos.getId() == pointOfSaleDTO.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Point Of Sale not found."));
        existingPoint.setName(pointOfSaleDTO.getName());
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives a point of sale identifier and removes it.
     * @param pointOfSaleId
     */
    public void deletePointOfSale(int pointOfSaleId) {
        boolean removed = pointOfSaleDTOList.removeIf(pointOfSale -> pointOfSale.getId() == pointOfSaleId);
        if(!removed){
            throw new IllegalArgumentException("Point Of Sale does not exist.");
        }
    }
}
