package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.java_challenge.constants.PointOfSaleConstants.*;

@Service
public class PointOfSaleServiceImpl implements PointOfSaleService {

    private final PointOfSaleRepository pointOfSaleRepository;

    public PointOfSaleServiceImpl(PointOfSaleRepository pointOfSaleRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    /**
     * <b>GET Method:</b><br>
     * This method gets the List of point of sale and returns it.
     *
     * @return List<PointOfSale>
     */
    @Cacheable(value= "pointOfSales", key= "'page=' + #page + ',size=' + #size")
    @Override
    public List<PointOfSale> getAllPointOfSale(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pointOfSaleRepository.findAll(pageable).getContent();
    }

    /**
    /**
     * <b>POST Method:</b><br>
     * This method receives a PointOfSale and adds it to the point Of Sale List.
     * @param pointOfSaleDTO
     */
    @CacheEvict(value="pointOfSale", allEntries=true)
    @Override
    @Transactional
    public PointOfSale createNewPointOfSale(PointOfSaleDTO pointOfSaleDTO) {
        if(pointOfSaleDTO == null) {
            throw new IllegalArgumentException(POS_CANNOT_BE_NULL);
        }
        if(pointOfSaleDTO.getName() == null || pointOfSaleDTO.getName().isEmpty()){
            throw new IllegalArgumentException(POS_CANNOT_BE_EMPTY);
        }

        PointOfSale newPointOfSale = new PointOfSale();
        newPointOfSale.setName(pointOfSaleDTO.getName());
        return pointOfSaleRepository.save(newPointOfSale);
    }

    /**
     * <b>PUT Method:</b><br>
     * This method receives the identifier of the Point of sale and the name to be modified.
     * @param id
     * @param name
     */
    @CacheEvict(value="pointOfSale", allEntries=true)
    @Override
    public PointOfSale updatePointOfSale(int id, String name) throws PointOfSaleNotFoundException {
        PointOfSale existingPoint = pointOfSaleRepository.findById(id)
                .orElseThrow(() -> new PointOfSaleNotFoundException(id));
        existingPoint.setName(name);
        return pointOfSaleRepository.save(existingPoint);
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives a point of sale identifier and removes it.
     * @param pointOfSaleId
     */
    @CacheEvict(value="pointOfSale", allEntries=true)
    @Override
    public void deletePointOfSale(int pointOfSaleId) throws PointOfSaleNotFoundException {
        if(!pointOfSaleRepository.existsById(pointOfSaleId)) {
            throw new PointOfSaleNotFoundException(pointOfSaleId);
        }
        pointOfSaleRepository.deleteById(pointOfSaleId);
    }
}
