package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.services.CostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private CostService costService;

    private List<PointOfSaleCostDTO> costsList;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
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
    void controllerTestGetCostList() throws Exception {

        mockMvc.perform(get("/costs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void controllerTestPostNewCost() throws Exception {

        PointOfSaleCostDTO cost = new PointOfSaleCostDTO(2,10,44);
        String jsonCost = objectMapper.writeValueAsString(cost);

        mockMvc.perform(post("/costs")
                .contentType(MediaType.APPLICATION_JSON).content(jsonCost))
                .andExpect(status().isOk());
    }

    @Test
    void controllerTestSearchCost() throws Exception {

        mockMvc.perform(get("/costs/search/9"))
                .andExpect(status().isOk())
                .andExpect(content().string("Id 9 has direct path to Id 8 with cost: 11\n"));
    }

    @Test
    void deleteTestController() throws Exception {

        mockMvc.perform(delete("/costs")
                .param("idA", "1")
                .param("idB", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cost between id 1 and id 2 has been deleted."));
    }

    @Test
    void nonExistingDeleteTestController() throws Exception {

        mockMvc.perform(delete("/costs")
                        .param("idA", "99")
                        .param("idB", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("There is no direct path between id 99 and id 1."));
    }
}
