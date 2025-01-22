package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.dtos.PointOfSale;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PointOfSaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<PointOfSale> pointOfSaleList;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        pointOfSaleList = new ArrayList<>();
        pointOfSaleList.add(new PointOfSale(1, "CABA"));
        pointOfSaleList.add(new PointOfSale(2, "GBA_1"));
        pointOfSaleList.add(new PointOfSale(3, "GBA_2"));
        pointOfSaleList.add(new PointOfSale(4, "Santa Fe"));
        pointOfSaleList.add(new PointOfSale(5, "Córdoba"));
        pointOfSaleList.add(new PointOfSale(6, "Misiones"));
        pointOfSaleList.add(new PointOfSale(7, "Salta"));
        pointOfSaleList.add(new PointOfSale(8, "Chubut"));
        pointOfSaleList.add(new PointOfSale(9, "Santa Cruz"));
        pointOfSaleList.add(new PointOfSale(10, "Catamarca"));
    }

    @Test
    void controllerTestGetPointOfSale() throws Exception {

        mockMvc.perform(get("/point-of-sale")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void controllerTestPostNewPointOfSale() throws Exception {

        PointOfSale pointOfSale = new PointOfSale(11, "Chaco");
        String jsonPointOfSale = objectMapper.writeValueAsString(pointOfSale);

        mockMvc.perform(MockMvcRequestBuilders.post("/point-of-sale")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPointOfSale))
                .andExpect(status().isOk());
    }

    @Test
    void controllerTestUpdatePointOfSale() throws Exception {

        PointOfSale pointOfSale = new PointOfSale(11, "Chaco");

        mockMvc.perform(MockMvcRequestBuilders.put("/point-of-sale")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSale))
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void controllerTestDeletePointOfSale() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/point-of-sale/{id}", 1)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
