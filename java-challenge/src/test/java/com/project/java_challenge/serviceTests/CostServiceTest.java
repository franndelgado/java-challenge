package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.services.CostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CostServiceTest {

    private CostService costService;
    private List<PointOfSaleCostDTO> costsList;

    @BeforeEach
    void setUp() {
        costService = new CostService();
        costsList = new ArrayList<>();
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

    @Test
    void testGetCostsList(){

        List<PointOfSaleCostDTO> costList = costService.getCostsList();
        assertThat(costList).isNotNull();
        assertThat(costList.size()).isEqualTo(14);
    }

    @Test
    void testCreateNewPointOfSaleCost(){

        PointOfSaleCostDTO newPointOfSaleCostDTO = new PointOfSaleCostDTO(5,7,9);
        costService.createNewPointOfSaleCost(newPointOfSaleCostDTO);
        List<PointOfSaleCostDTO> costList = costService.getCostsList();
        assertThat(costList).isNotNull();
        assertThat(costList.size()).isEqualTo(15);
        assert(costList).contains(newPointOfSaleCostDTO);
    }


    @Test
    void testDeletePointOfSaleCost(){

        String stringResult = costService.deletePointOfSaleCost(1, 2);
        assertEquals("Cost between id 1 and id 2 has been deleted.", stringResult);
    }

    @Test
    void testDeletePointOfSaleCost2(){

        String stringResult = costService.deletePointOfSaleCost(2, 1);
        assertEquals("Cost between id 2 and id 1 has been deleted.", stringResult);
    }


    @Test
    void testDeleteNonExistingPointOfSaleCost(){

        String errorDelete = costService.deletePointOfSaleCost(99, 1);
        assertEquals("There is no direct path between id 99 and id 1.", errorDelete);
    }


    @Test
    void testNewSearchPointOfSaleCost(){

        String result = costService.searchPointOfSaleCost(6);
        assertTrue(result.contains("Id 6 has direct path to Id 7 with cost: 32"));
    }
}
