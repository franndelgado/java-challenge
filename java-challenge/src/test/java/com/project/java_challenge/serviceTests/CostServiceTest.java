package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.services.CostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CostServiceTest {

    @InjectMocks
    private CostService costService;

    @Mock
    private PointOfSaleCostRepository pointOfSaleCostRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCostsList(){
        List<PointOfSaleCost> expectedCostsList = Arrays.asList(new PointOfSaleCost(1, 9, 100));
        when(pointOfSaleCostRepository.findAll()).thenReturn(expectedCostsList);

        List<PointOfSaleCost> actualCostsList = costService.getCostsList();

        assertEquals(expectedCostsList, actualCostsList);
    }


    @Test
    void testCreateNewPointOfSaleCost(){

        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 9, 100);
        PointOfSaleCost newPointOfSaleCost = new PointOfSaleCost(1, 9, 100);

        when(pointOfSaleCostRepository.save(any(PointOfSaleCost.class))).thenReturn(newPointOfSaleCost);

        PointOfSaleCost result = costService.createNewPointOfSaleCost(pointOfSaleCostDTO);
        assertNotNull(result);
    }


    @Test
    void testDeletePointOfSaleCost(){

        PointOfSaleCost newPointOfSaleCost = new PointOfSaleCost(1, 2, 100);
        when(pointOfSaleCostRepository.findByIdAAndIdB(1, 2)).thenReturn(Optional.of(newPointOfSaleCost));

        String stringResult = costService.deletePointOfSaleCost(1, 2);

        assertEquals("Successfully deleted point Of Sale Cost.", stringResult);
    }

    @Test
    void testDeleteNonExistingPointOfSaleCost(){

        when(pointOfSaleCostRepository.findByIdAAndIdB(99, 9)).thenReturn(Optional.empty());

        String stringResult = costService.deletePointOfSaleCost(99, 9);

        assertEquals("There is no direct path between id 99 and id 9 .", stringResult);
    }

    @Test
    void testNewSearchPointOfSaleCost(){

        PointOfSaleCost cost1 = new PointOfSaleCost(1, 2, 100);
        PointOfSaleCost cost2 = new PointOfSaleCost(1, 3, 200);

        when(pointOfSaleCostRepository.findByIdAOrIdB(1, 1)).thenReturn(Arrays.asList(cost1, cost2));

        String result = costService.searchPointOfSaleCost(1).trim();

        String expectedLines = """
                Id 1 has direct path to Id 2 with cost: 100
                Id 1 has direct path to Id 3 with cost: 200
                """.trim();

        assertEquals(expectedLines, result);
    }
}
