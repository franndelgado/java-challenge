package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.exceptions.PointOfSaleCostNotFoundException;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.project.java_challenge.constants.PointOfSaleCostConstants.*;

@Service
public class CostServiceImpl implements CostService {

    private final PointOfSaleCostRepository pointOfSaleCostRepository;

    public CostServiceImpl(PointOfSaleCostRepository pointOfSaleCostRepository) {
        this.pointOfSaleCostRepository = pointOfSaleCostRepository;
    }

    /**
     * GET Method:
     * This method gets the List of costs and returns it.
     * @return List<PointOfSaleCost>
     */
    @Cacheable(value = "costListCache")
    @Override
    public List<PointOfSaleCost> getCostsList() {
        return pointOfSaleCostRepository.findAll();
    }

    /**
     * POST Method:
     * This method receives a PointOfSaleCost and adds it to the List of costs.
     * @param pointOfSaleCostDTO
     */
    @CacheEvict(value = {"costListCache", "costByIdCache"}, allEntries = true)
    @Override
    public PointOfSaleCost createNewPointOfSaleCost(PointOfSaleCostDTO pointOfSaleCostDTO) {
        if (Optional.ofNullable(pointOfSaleCostDTO.getIdA()).isEmpty() || Optional.ofNullable(pointOfSaleCostDTO.getIdB()).isEmpty()
        || Optional.ofNullable(pointOfSaleCostDTO.getCost()).isEmpty()) {
            throw new IllegalArgumentException(COST_CANNOT_BE_NULL);
        }

        if(pointOfSaleCostDTO.getIdA() <= 0 || pointOfSaleCostDTO.getIdB() <= 0) {
            throw new IllegalArgumentException(IDS_CANNOT_BE_NEGATIVE);
        }

        if(pointOfSaleCostDTO.getCost() < 0){
            throw new IllegalArgumentException(COST_CANNOT_BE_NEGATIVE);
        }

        if(Objects.equals(pointOfSaleCostDTO.getIdA(), pointOfSaleCostDTO.getIdB())){
            throw new IllegalArgumentException(IDS_CANNOT_BE_IDENTICAL);
        }

        Optional<PointOfSaleCost> existingCost = pointOfSaleCostRepository.findByIdAAndIdB(pointOfSaleCostDTO.getIdA(), pointOfSaleCostDTO.getIdB());
        if(existingCost.isPresent()){
            throw new IllegalArgumentException(COST_ALREADY_EXISTS);
        }

        PointOfSaleCost newPointOfSaleCost = new PointOfSaleCost();
        newPointOfSaleCost.setIdA(pointOfSaleCostDTO.getIdA());
        newPointOfSaleCost.setIdB(pointOfSaleCostDTO.getIdB());
        newPointOfSaleCost.setCost(pointOfSaleCostDTO.getCost());

        return pointOfSaleCostRepository.save(newPointOfSaleCost);
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives idA and idB and iterates through the list of cost, if it finds both parameters it deletes
     * the object. Otherwise, returns error message.
     * @param idA
     * @param idB
     * @return String
     */
    @CacheEvict(value = {"costListCache", "costByIdCache"}, allEntries = true)
    @Override
    public void deletePointOfSaleCost(int idA, int idB){

        Optional<PointOfSaleCost> pointOfSaleCost = pointOfSaleCostRepository.findByIdAAndIdB(idA, idB);

        if(pointOfSaleCost.isEmpty()){
            pointOfSaleCost = pointOfSaleCostRepository.findByIdBAndIdA(idA, idB);
        }

        if(pointOfSaleCost.isPresent()){
            pointOfSaleCostRepository.delete(pointOfSaleCost.get());
        }
        else {
            throw new PointOfSaleCostNotFoundException(idA, idB);
        }
    }

    /**
     * GET Method:
     * This receives an id and search in the list of costs for matches.
     * Returns a string with the number of direct path and their costs.
     * @param id
     * @return String
     */
    @Cacheable(value = "costByIdCache", key = "#id")
    @Override
    public String searchPointOfSaleCost(Integer id){

        if(id == null){
            return String.valueOf(new IllegalArgumentException(MISSING_ID_ERROR));
        }

        StringBuilder finalResponse = new StringBuilder();
        List<PointOfSaleCost> directPaths = pointOfSaleCostRepository.findByIdAOrIdB(id, id);

        if(directPaths.isEmpty()){
            return String.format("No direct path between id %d and id %d.", id, id);
        }
        for(PointOfSaleCost pointOfSaleCost : directPaths) {
            if(Objects.equals(pointOfSaleCost.getIdA(), id)){
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCost.getIdB()).append(" with cost: ").append(pointOfSaleCost.getCost()).append("\n");
            } else if (Objects.equals(pointOfSaleCost.getIdB(), id)) {
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCost.getIdA()).append(" with cost: ").append(pointOfSaleCost.getCost()).append("\n");
            }
        }
        return finalResponse.toString();
    }
}
