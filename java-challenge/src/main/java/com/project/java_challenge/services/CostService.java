package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.entities.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CostService {

    private final PointOfSaleCostRepository pointOfSaleCostRepository;

    public CostService(PointOfSaleCostRepository pointOfSaleCostRepository) {
        this.pointOfSaleCostRepository = pointOfSaleCostRepository;
    }

    /**
     * GET Method:
     * This method gets the List of costs and returns it.
     * @return List<PointOfSaleCost>
     */
    public List<PointOfSaleCost> getCostsList() {
        return pointOfSaleCostRepository.findAll();
    }

    /**
     * POST Method:
     * This method receives a PointOfSaleCost and adds it to the List of costs.
     * @param pointOfSaleCostDTO
     */
    public PointOfSaleCost createNewPointOfSaleCost(PointOfSaleCostDTO pointOfSaleCostDTO) {
        if (pointOfSaleCostDTO.getIdA() == null || pointOfSaleCostDTO.getIdB() == null || pointOfSaleCostDTO.getCost() == null) {
            throw new IllegalArgumentException("Point Of Sale Cost cannot be null.");
        } else {
            PointOfSaleCost newPointOfSaleCost = new PointOfSaleCost();
            newPointOfSaleCost.setIdA(pointOfSaleCostDTO.getIdA());
            newPointOfSaleCost.setIdB(pointOfSaleCostDTO.getIdB());
            newPointOfSaleCost.setCost(pointOfSaleCostDTO.getCost());

            return pointOfSaleCostRepository.save(newPointOfSaleCost);
        }
    }

    /**
     * <b>DELETE Method:</b><br>
     * This method receives idA and idB and iterates through the list of cost, if it finds both parameters it deletes
     * the object. Otherwise, returns error message.
     * @param idA
     * @param idB
     * @return String
     */
    public String deletePointOfSaleCost(int idA, int idB){

        Optional<PointOfSaleCost> pointOfSaleCost = pointOfSaleCostRepository.findByIdAAndIdB(idA, idB);

        if(pointOfSaleCost.isEmpty()){
            pointOfSaleCost = pointOfSaleCostRepository.findByIdBAndIdA(idA, idB);
        }

        if(pointOfSaleCost.isPresent()){
            pointOfSaleCostRepository.delete(pointOfSaleCost.get());
            return "Successfully deleted pointOfSaleCost";
        }
        return String.format("There is no direct path between id %d and id %d .", idA, idB );
    }

    /**
     * GET Method:
     * This receives an id and search in the list of costs for matches.
     * Returns a string with the number of direct path and their costs.
     * @param id
     * @return String
     */
    public String searchPointOfSaleCost(Integer id){

        if(id == null){
            return String.valueOf(new IllegalArgumentException("Id is missing."));
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
