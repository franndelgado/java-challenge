package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.entities.PointOfSale;
import com.project.java_challenge.entities.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.CostService;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CostServiceTest {

    private List<PointOfSaleCostDTO> costsList;

    @InjectMocks
    private CostService costService;

    @Mock
    private PointOfSaleCostRepository pointOfSaleCostRepository;

    private PointOfSaleCostDTO pointOfSaleCostDTO;
    private PointOfSaleCost pointOfSaleCost;

    @BeforeEach
    void setUp() {
        costService = new CostService(pointOfSaleCostRepository);

        pointOfSaleCost = new PointOfSaleCost();
        pointOfSaleCost.setIdA(1);
        pointOfSaleCost.setIdB(2);
        pointOfSaleCost.setCost(111);
        costsList = new ArrayList<>();

    }

    @Test
    void testGetCostsList(){

        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(pointOfSaleCost));
        costService.getCostsList();

        verify(pointOfSaleCostRepository).findAll();
    }

    /**
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
    */
}
