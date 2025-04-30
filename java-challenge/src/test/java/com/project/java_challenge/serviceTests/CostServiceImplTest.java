package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.exceptions.PointOfSaleCostNotFoundException;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.services.CostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostServiceImplTest {

    @Mock
    private PointOfSaleCostRepository pointOfSaleCostRepository;

    @InjectMocks
    private CostServiceImpl costService;

    @Test
    void shouldReturnListOfPointOfSaleCost() {
        List<PointOfSaleCost> mockList = List.of(new PointOfSaleCost());
        when(pointOfSaleCostRepository.findAll()).thenReturn(mockList);

        List<PointOfSaleCost> result = costService.getCostsList();

        assertEquals(mockList, result);
        assertEquals(1, result.size());
        verify(pointOfSaleCostRepository).findAll();
    }

    //----------------------------- Create New Point of Sale Cost Tests ---------------------------

    @Test
    void testCreateNewPointOfSaleCost_ThrowsIfNullFields() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 2, 100);
        when(pointOfSaleCostRepository.findByIdAAndIdB(1, 2)).thenReturn(Optional.empty());

        PointOfSaleCost saved = new PointOfSaleCost();
        saved.setIdA(1);
        saved.setIdB(2);
        saved.setCost(100);

        when(pointOfSaleCostRepository.save(any(PointOfSaleCost.class))).thenReturn(saved);

        PointOfSaleCost result = costService.createNewPointOfSaleCost(pointOfSaleCostDTO);

        assertEquals(saved, result);
        verify(pointOfSaleCostRepository).save(any(PointOfSaleCost.class));
    }

    @Test
    void createdNewPointOfSaleCost_ThrowsWhenAnyFieldIsNull() {
        PointOfSaleCostDTO dtoWithIdANull = new PointOfSaleCostDTO(null, 2, 100);
        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(dtoWithIdANull));

        PointOfSaleCostDTO dtoWithIdBNull = new PointOfSaleCostDTO(1, null, 100);
        assertThrows(IllegalArgumentException.class, () -> costService.createNewPointOfSaleCost(dtoWithIdBNull));

        PointOfSaleCostDTO dtoWithCostNull = new PointOfSaleCostDTO(1, 2, null);
        assertThrows(IllegalArgumentException.class, () -> costService.createNewPointOfSaleCost(dtoWithCostNull));
    }

    @Test
    void createNewPointOfSaleCost_throwsWhenIdAIsNegativeOrZero() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(0, 2, 100);
        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(pointOfSaleCostDTO));
    }

    @Test
    void createNewPointOfSaleCost_throwsWhenIdBIsNegativeOrZero() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, -2, 100);
        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(pointOfSaleCostDTO));
    }

    @Test
    void createNewPointOfSaleCost_throwsWhenCostIsNegativeOrZero() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 2, -99);
        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(pointOfSaleCostDTO));
    }

    @Test
    void createNewPointOfSaleCost_throwsWhenIdAIsEqualToIdB() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 1, 100);
        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(pointOfSaleCostDTO));
    }

    @Test
    void createNewPointOfSaleCost_throwsWhenAlreadyExists() {
        PointOfSaleCostDTO pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 2, 100);
        when(pointOfSaleCostRepository.findByIdAAndIdB(1, 2)).thenReturn(Optional.of(new PointOfSaleCost()));

        assertThrows(IllegalArgumentException.class, () ->  costService.createNewPointOfSaleCost(pointOfSaleCostDTO));
    }

    //------------------------- Delete A Point of Sale Cost Tests: ----------------------------------
    @Test
    void shouldDeleteAPointOfSaleCost(){
        int idA = 1, idB = 2;
        PointOfSaleCost pointOfSaleCost = new PointOfSaleCost();
        when(pointOfSaleCostRepository.findByIdAAndIdB(idA, idB)).thenReturn(Optional.of(pointOfSaleCost));

        costService.deletePointOfSaleCost(idA, idB);

        verify(pointOfSaleCostRepository).delete(pointOfSaleCost);
    }

    @Test
    void testDeleteNonExistingPointOfSaleCost(){
        when(pointOfSaleCostRepository.findByIdAAndIdB(99, 9)).thenReturn(Optional.empty());

        assertThrows(PointOfSaleCostNotFoundException.class, () -> costService.deletePointOfSaleCost(99, 9));
    }

    //--------------------- Search a Point of Sale Cost Tests: --------------------------------
    @Test
    void testSearchPointOfSaleCost(){

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

    @Test
    void searchPointOfSaleCost_returnsErrorIfIdIsNull(){
        String stringResult = costService.searchPointOfSaleCost(null).trim();
        assertTrue(stringResult.contains("java.lang.IllegalArgumentException"));
    }

    @Test
    void searchPointOfSaleCost_returnsNoDirectPath(){
        when(pointOfSaleCostRepository.findByIdAOrIdB(1, 1)).thenReturn(List.of());

        String result = costService.searchPointOfSaleCost(1).trim();

        assertEquals("No direct path between id 1 and id 1.", result);
    }

    @Test
    void searchPointOfSaleCost_whenIdMatchesIdB() {
        int id = 2;
        PointOfSaleCost cost = new PointOfSaleCost();
        cost.setIdA(1);
        cost.setIdB(id);
        cost.setCost(100);

        when(pointOfSaleCostRepository.findByIdAOrIdB(id, id)).thenReturn(List.of(cost));

        String result = costService.searchPointOfSaleCost(id).trim();

        assertTrue(result.contains("Id 2 has direct path to Id 1 with cost: 100"));
    }
}
