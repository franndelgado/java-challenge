package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfSaleService {

    private final PointOfSaleRepository pointOfSaleRepository;

    public PointOfSaleService(PointOfSaleRepository pointOfSaleRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    /**
     * <b>GET Method:</b><br>
     * This method gets the List of point of sale and returns it.
     *
     * @return List<PointOfSale>
     */
    public List<PointOfSale> getPointOfSale() {
        return pointOfSaleRepository.findAll();
    }

    /**
    /**
     * <b>POST Method:</b><br>
     * This method receives a PointOfSale and adds it to the point Of Sale List.
     * @param pointOfSaleDTO
     */
    @Transactional
    public PointOfSale createNewPointOfSale(PointOfSaleDTO pointOfSaleDTO) {
        if(pointOfSaleDTO == null) {
            throw new IllegalArgumentException("Point Of Sale cannot be null.");
        }
        if(pointOfSaleDTO.getName() == null || pointOfSaleDTO.getName().isEmpty()){
            throw new IllegalArgumentException("Point Of Sale name cannot be empty.");
        }
        PointOfSale newPointOfSale = new PointOfSale();
        newPointOfSale.setName(pointOfSaleDTO.getName());
        return pointOfSaleRepository.save(newPointOfSale);
    }

    /**
     * <b>PUT Method:</b><br>
     * This method receives a PointOfSale and updates it.
     * @param pointOfSaleDTO
     */

    public PointOfSale updatePointOfSale(PointOfSaleDTO pointOfSaleDTO) {

        PointOfSale existingPoint = pointOfSaleRepository.findById(pointOfSaleDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Point Of Sale not found."));
        existingPoint.setName(pointOfSaleDTO.getName());
        return pointOfSaleRepository.save(existingPoint);
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives a point of sale identifier and removes it.
     * @param pointOfSaleId
     */
    public void deletePointOfSale(int pointOfSaleId) {
        if(!pointOfSaleRepository.existsById(pointOfSaleId)) {
            throw new IllegalArgumentException("Point Of Sale not found.");
        }
        pointOfSaleRepository.deleteById(pointOfSaleId);
    }
}
