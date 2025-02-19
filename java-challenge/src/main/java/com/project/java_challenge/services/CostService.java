package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CostService {

    private final List<PointOfSaleCostDTO> costsList = new ArrayList<>();

    public CostService(){
        costsList.add(new PointOfSaleCostDTO(1,2,2));
        costsList.add(new PointOfSaleCostDTO(1,3,3));
        costsList.add(new PointOfSaleCostDTO(2,3,5));
        costsList.add(new PointOfSaleCostDTO(2,4,10));
        costsList.add(new PointOfSaleCostDTO(1,4,11));
        costsList.add(new PointOfSaleCostDTO(4,5,5));
        costsList.add(new PointOfSaleCostDTO(2,5,14));
        costsList.add(new PointOfSaleCostDTO(6,7,32));
        costsList.add(new PointOfSaleCostDTO(8,9,11));
        costsList.add(new PointOfSaleCostDTO(10,7,5));
        costsList.add(new PointOfSaleCostDTO(3,8,10));
        costsList.add(new PointOfSaleCostDTO(5,8,30));
        costsList.add(new PointOfSaleCostDTO(10,5,5));
        costsList.add(new PointOfSaleCostDTO(4,6,6));
    }

    /**
     * GET Method:
     * This method gets the List of costs and returns it.
     * @return List<PointOfSaleCost>
     */
    public List<PointOfSaleCostDTO> getCostsList() {
        return costsList;
    }

    /**
     * POST Method:
     * This method receives a PointOfSaleCost and adds it to the List of costs.
     * @param pointOfSaleCostDTO
     */
    public void createNewPointOfSaleCost(PointOfSaleCostDTO pointOfSaleCostDTO){
        if(pointOfSaleCostDTO.getIdA() == null || pointOfSaleCostDTO.getIdB() == null || pointOfSaleCostDTO.getCost() == null) {
            throw new IllegalArgumentException("Point Of Sale Cost cannot be null.");
        } else {
            costsList.add(pointOfSaleCostDTO);
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
        for(PointOfSaleCostDTO pointOfSaleCostDTO : costsList) {
            if ((pointOfSaleCostDTO.getIdA() == idA || pointOfSaleCostDTO.getIdA() == idB) &&
                    (pointOfSaleCostDTO.getIdB() == idB || pointOfSaleCostDTO.getIdB() == idA)) {
                costsList.remove(pointOfSaleCostDTO);
                return String.format("Cost between id %d and id %d has been deleted.", idA, idB );
            }
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
    public String searchPointOfSaleCost(int id){

        if(id <= 1){
            throw new IllegalArgumentException("Id must be greater than 1.");
        }

        StringBuilder finalResponse = new StringBuilder();
        for(PointOfSaleCostDTO pointOfSaleCostDTO : costsList) {
            if(pointOfSaleCostDTO.getIdA() == id){
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCostDTO.getIdB()).append(" with cost: ").append(pointOfSaleCostDTO.getCost()).append("\n");
            } else if (pointOfSaleCostDTO.getIdB() == id) {
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCostDTO.getIdA()).append(" with cost: ").append(pointOfSaleCostDTO.getCost()).append("\n");
            }
        }
        return finalResponse.toString();
    }
}
