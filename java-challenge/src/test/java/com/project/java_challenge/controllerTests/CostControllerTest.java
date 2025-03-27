package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.entities.PointOfSaleCost;
import com.project.java_challenge.repositories.PointOfSaleCostRepository;
import com.project.java_challenge.services.CostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CostService costService;

    @Mock
    private PointOfSaleCostRepository pointOfSaleCostRepository;

    private PointOfSaleCost pointOfSaleCost;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pointOfSaleCost = new PointOfSaleCost();
        pointOfSaleCost.setIdA(1);
        pointOfSaleCost.setIdB(2);
        pointOfSaleCost.setCost(100);
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
    void nonExistingDeleteTestController() throws Exception {

        mockMvc.perform(delete("/costs")
                        .param("idA", "99")
                        .param("idB", "1"))
                .andExpect(content().string("There is no direct path between id 99 and id 1 ."));
    }
}
