package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.entities.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PointOfSaleDTOControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private PointOfSale pointOfSale;

    @BeforeEach
    void setUp() {
        pointOfSale = new PointOfSale();
        pointOfSale.setId(1);
        pointOfSale.setName("CABA");
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

        PointOfSaleDTO pointOfSaleDTO = new PointOfSaleDTO(1, "CABA");
        mockMvc.perform(MockMvcRequestBuilders.post("/point-of-sale")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pointOfSaleDTO)))
                .andExpect(status().isOk());

        PointOfSaleDTO updatedPointOfSaleDTO = new PointOfSaleDTO(1, "Chaco");
        mockMvc.perform(MockMvcRequestBuilders.put("/point-of-sale")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPointOfSaleDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void controllerTestDeletePointOfSale_BadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/point-of-sale/{id}", 99)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
