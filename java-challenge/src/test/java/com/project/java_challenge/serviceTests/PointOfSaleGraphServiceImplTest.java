package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.exceptions.MinimumCostPathException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.PointOfSaleGraphServiceImpl;
import com.project.java_challenge.services.PointOfSaleService;
import com.project.java_challenge.utils.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PointOfSaleGraphServiceImplTest {

    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    @Mock
    private PointOfSaleCostRepository pointOfSaleCostRepository;

    @Mock
    private PointOfSaleService pointOfSaleService;

    @InjectMocks
    private PointOfSaleGraphServiceImpl pointOfSaleGraphService;

    @BeforeEach
    void setUp() {
        pointOfSaleCostRepository = mock(PointOfSaleCostRepository.class);
        pointOfSaleRepository = mock(PointOfSaleRepository.class);
        pointOfSaleService = mock(PointOfSaleService.class);
        pointOfSaleGraphService = new PointOfSaleGraphServiceImpl(pointOfSaleRepository, pointOfSaleCostRepository);
    }

    @Test
    void getMinimumCostPath_shouldReturnDirectPath() throws MinimumCostPathException {
        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(new PointOfSaleCost(1, 2, 2)));
        when(pointOfSaleRepository.findById(1)).thenReturn(Optional.of(new PointOfSale(1, "CABA")));
        when(pointOfSaleRepository.findById(2)).thenReturn(Optional.of(new PointOfSale(2, "GBA_1")));

        MinimumCostPathResponseDTO response = pointOfSaleGraphService.getMinimumCostPath(1, 2);

        assertEquals(2, response.getTotalCost());
        assertEquals(List.of("CABA", "GBA_1"), response.getPath());
    }

    @Test
    void getMinimumCostPath_shouldChooseShortestPath() throws MinimumCostPathException {
        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(new PointOfSaleCost(1, 2, 5),
                new PointOfSaleCost(2, 3, 2),
                new PointOfSaleCost(1, 3, 10)));

        when(pointOfSaleRepository.findById(anyInt())).thenAnswer(invocation ->
                Optional.of(new PointOfSale(invocation.getArgument(0), "P" + invocation.getArgument(0))));

        MinimumCostPathResponseDTO response = pointOfSaleGraphService.getMinimumCostPath(1, 3);

        assertEquals(7, response.getTotalCost());
        assertEquals(List.of("P1", "P2", "P3"), response.getPath());
    }

    @Test
    void getMinimumCostPath_shouldThrowWhenNotFound() {

        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(
                new PointOfSaleCost(99, 100, 5),
                new PointOfSaleCost(101, 1002, 7)
        ));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> pointOfSaleGraphService.getMinimumCostPath(99, 1002));

        assertTrue(exception.getMessage().contains("No path was found between the points of sale"));
    }

    @Test
    void getMinimumCostPath_shouldThrowWhenNodeNotInGraph() {
        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(new PointOfSaleCost(1, 2, 5)));

        MinimumCostPathException exception = assertThrows(MinimumCostPathException.class, () -> pointOfSaleGraphService.getMinimumCostPath(1, 99));

        assertTrue(exception.getMessage().contains("One or both points of sale do not exist."));
    }

    @Test
    void buildGraph_shouldIgnoreNullIds() {
        when(pointOfSaleCostRepository.findAll()).thenReturn(List.of(new PointOfSaleCost(null, 2, 5),
                new PointOfSaleCost(1, null, 3),
                new PointOfSaleCost(1, 2, 2)));

        Map<Integer, List<Connection>> graph = ReflectionTestUtils.invokeMethod(pointOfSaleGraphService, "buildGraph");

        assertTrue(graph.containsKey(1));
        assertEquals(1, graph.get(1).size());
        assertEquals(2, graph.get(1).get(0).getDestination());
    }
}
