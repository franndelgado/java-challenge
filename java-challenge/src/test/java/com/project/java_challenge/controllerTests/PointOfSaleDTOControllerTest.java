package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.dtos.PointOfSaleDTO;
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
class PointOfSaleDTOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private List<PointOfSaleDTO> pointOfSaleDTOList;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        pointOfSaleDTOList = new ArrayList<>();
        pointOfSaleDTOList.add(new PointOfSaleDTO(1, "CABA"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(2, "GBA_1"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(3, "GBA_2"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(4, "Santa Fe"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(5, "Córdoba"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(6, "Misiones"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(7, "Salta"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(8, "Chubut"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(9, "Santa Cruz"));
        pointOfSaleDTOList.add(new PointOfSaleDTO(10, "Catamarca"));
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

        PointOfSaleDTO pointOfSaleDTO = new PointOfSaleDTO(11, "Chaco");
        String jsonPointOfSale = objectMapper.writeValueAsString(pointOfSaleDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/point-of-sale")
                        .contentType(MediaType.APPLICATION_JSON).content(jsonPointOfSale))
                .andExpect(status().isOk());
    }

    @Test
    void controllerTestUpdatePointOfSale() throws Exception {

        PointOfSaleDTO pointOfSaleDTO = new PointOfSaleDTO(11, "Chaco");

        mockMvc.perform(MockMvcRequestBuilders.put("/point-of-sale")
        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSaleDTO))
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
