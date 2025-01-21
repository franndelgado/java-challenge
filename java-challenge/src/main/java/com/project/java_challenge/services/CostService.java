package com.project.java_challenge.services;

import com.project.java_challenge.dtos.PointOfSaleCost;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CostService {

    private final List<PointOfSaleCost> costsList = new ArrayList<>();

    public CostService(){
        costsList.add(new PointOfSaleCost(1,2,2));
        costsList.add(new PointOfSaleCost(1,3,3));
        costsList.add(new PointOfSaleCost(2,3,5));
        costsList.add(new PointOfSaleCost(2,4,10));
        costsList.add(new PointOfSaleCost(1,4,11));
        costsList.add(new PointOfSaleCost(4,5,5));
        costsList.add(new PointOfSaleCost(2,5,14));
        costsList.add(new PointOfSaleCost(6,7,32));
        costsList.add(new PointOfSaleCost(8,9,11));
        costsList.add(new PointOfSaleCost(10,7,5));
        costsList.add(new PointOfSaleCost(3,8,10));
        costsList.add(new PointOfSaleCost(5,8,30));
        costsList.add(new PointOfSaleCost(10,5,5));
        costsList.add(new PointOfSaleCost(4,6,6));
    }

    public List<PointOfSaleCost> getCostsList() {
        return costsList;
    }

    public void createNewPointOfSaleCost(PointOfSaleCost pointOfSaleCost){
        costsList.add(pointOfSaleCost);
    }

    public String deletePointOfSaleCost(int idA, int idB){

        for(PointOfSaleCost pointOfSaleCost : costsList) {
            if ((pointOfSaleCost.getIdA() == idA || pointOfSaleCost.getIdA() == idB) &&
                    (pointOfSaleCost.getIdB() == idB || pointOfSaleCost.getIdB() == idA)) {
                costsList.remove(pointOfSaleCost);
                return String.format("Cost between id %d and id %d has been deleted.", idA, idB );
            }
        }
        return String.format("There is no direct path between id %d and id %d.", idA, idB );
    }

    /**
    public List<PointOfSaleCost> searchPointOfSaleCosts(int idA){
        List<PointOfSaleCost> costsList1 = new ArrayList<>();
        for(PointOfSaleCost pointOfSaleCost : costsList){
            if(pointOfSaleCost.getIdA() == idA || pointOfSaleCost.getIdB() == idA){
                costsList1.add(pointOfSaleCost);
            }
        }
        return costsList1;
    }
     */

    public String newSearchPointOfSaleCost(int id){

        StringBuilder finalResponse = new StringBuilder();
        for(PointOfSaleCost pointOfSaleCost : costsList) {
            if(pointOfSaleCost.getIdA() == id){
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCost.getIdB()).append(" with cost: ").append(pointOfSaleCost.getCost()).append("\n");
            } else if (pointOfSaleCost.getIdB() == id) {
                finalResponse.append("Id ").append(id).append(" has direct path to Id ").append(pointOfSaleCost.getIdA()).append(" with cost: ").append(pointOfSaleCost.getCost()).append("\n");
            }
        }
        return finalResponse.toString();
    }

    /**
    public List<PointOfSaleCost> getMinCost(int idA, int idB) {

        if(getList(idA, idB).isEmpty()){

        }
        else{

        }
    }
     */


    /**
    public List<PointOfSaleCost> getList(int idA, int idB) {
        List<PointOfSaleCost> costsListFilter = new ArrayList<>();

        for(PointOfSaleCost pointOfSaleCost : costsList) {
            if ((pointOfSaleCost.getIdA() == idA || pointOfSaleCost.getIdA() == idB) &&
                    (pointOfSaleCost.getIdB() == idB || pointOfSaleCost.getIdB() == idA)) {
                costsListFilter.add(pointOfSaleCost);
            }
        }
        return costsListFilter;
    }

     Stream<Integer> costos = searchPointOfSaleCosts(id).stream().map(costs -> costs.getCost());
     searchPointOfSaleCosts(id).stream().filter(lista -> lista.getCost() == costos).findFirst();

     */
}
