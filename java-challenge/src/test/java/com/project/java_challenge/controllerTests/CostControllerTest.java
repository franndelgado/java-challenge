package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.controllers.CostController;
import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.exceptions.PointOfSaleCostNotFoundException;
import com.project.java_challenge.models.PointOfSaleCost;
import com.project.java_challenge.services.CostService;
import com.project.java_challenge.services.PointOfSaleGraphService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CostController.class)
@AutoConfigureMockMvc(addFilters = false)
class CostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CostService costService;

    @MockBean
    private PointOfSaleGraphService pointOfSaleGraphService;

    private PointOfSaleCost pointOfSaleCost;

    @Autowired
    private ObjectMapper objectMapper;

    private PointOfSaleCostDTO pointOfSaleCostDTO;

    @BeforeEach
    void setUp() {
        pointOfSaleCost = new PointOfSaleCost(1, 2, 100);

        pointOfSaleCostDTO = new PointOfSaleCostDTO(1, 2, 100);
    }

    @Test
    void controllerTestGetCostList() throws Exception {

        when(costService.getCostsList()).thenReturn(List.of(pointOfSaleCost));

        mockMvc.perform(get("/api/costs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idA").value(1))
                .andExpect(jsonPath("$[0].idB").value(2))
                .andExpect(jsonPath("$[0].cost").value(100));
    }

    @Test
    void controllerTestPostNewCost() throws Exception {

        when(costService.createNewPointOfSaleCost(any())).thenReturn(pointOfSaleCost);

        mockMvc.perform(post("/api/costs")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSaleCostDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idA").value(1));
    }

    @Test
    void addNewPointOfSaleCost_shouldReturnBadRequestOnIllegalArgumentException() throws Exception {
        when(costService.createNewPointOfSaleCost(any())).thenThrow(new IllegalArgumentException());

        mockMvc.perform(post("/api/costs")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSaleCostDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addNewPointOfSaleCost_shouldReturnInternalServerError() throws Exception {
        when(costService.createNewPointOfSaleCost(any())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/costs")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSaleCostDTO)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deletePointOfSaleCost() throws Exception {
        doNothing().when(costService).deletePointOfSaleCost(1,2);

        mockMvc.perform(delete("/api/costs")
                .param("idA", "1")
                .param("idB", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePointOfSaleCost_shouldReturnNotFound() throws Exception {
        doThrow(PointOfSaleCostNotFoundException.class).when(costService).deletePointOfSaleCost(1,99);

        mockMvc.perform(delete("/api/costs")
                        .param("idA", "1")
                        .param("idB", "99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void controllerTestSearchCost() throws Exception {

        when(costService.searchPointOfSaleCost(9)).thenReturn("Id 9 has direct path to Id 8 with cost: 11\n");

        mockMvc.perform(get("/api/costs/search/9"))
                .andExpect(status().isOk())
                .andExpect(content().string("Id 9 has direct path to Id 8 with cost: 11\n"));
    }

    @Test
    void getMinimumPath_ShouldReturnValidPath() throws Exception {
        List<String> expectedPath = List.of("A", "B", "C");
        MinimumCostPathResponseDTO response = new MinimumCostPathResponseDTO(10, expectedPath);

        when(pointOfSaleGraphService.getMinimumCostPath(1, 3)).thenReturn(response);

        mockMvc.perform(get("/api/costs/minimumPath")
                .param("from", "1")
                .param("to", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(10))
                .andExpect(jsonPath("$.path[0]").value("A"));
    }
}
