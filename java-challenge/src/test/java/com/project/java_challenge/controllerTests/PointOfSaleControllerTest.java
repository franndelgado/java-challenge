package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.controllers.PointOfSaleController;
import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.eq;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PointOfSaleController.class)
@AutoConfigureMockMvc(addFilters = false)
class PointOfSaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointOfSaleService pointOfSaleService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        PointOfSale pointOfSale = new PointOfSale(1, "CABA");
    }

    @Test
    void controllerTestGetPointOfSale() throws Exception {
        List<PointOfSale> pointOfSaleList = IntStream.range(0, 10)
                        .mapToObj(i -> new PointOfSale( i, "POS" + i))
                .collect(Collectors.toList());

        when(pointOfSaleService.getAllPointOfSale(0, 10)).thenReturn(pointOfSaleList);

        mockMvc.perform(get("/api/point-of-sale?page=0&size=10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void controllerTestPostNewPointOfSale() throws Exception {
        PointOfSaleDTO pointOfSaleDTO = new PointOfSaleDTO(11, "Chaco");
        PointOfSale created = new PointOfSale(11, "Chaco");

        when(pointOfSaleService.createNewPointOfSale(any(PointOfSaleDTO.class))).thenReturn(created);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/point-of-sale")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pointOfSaleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.name").value("Chaco"));
    }

    @Test
    void controllerTestUpdatePointOfSale() throws Exception {
        int id = 11;
        String name = "Nuevo Chaco";
        PointOfSale updated = new PointOfSale(id, name);

        when(pointOfSaleService.updatePointOfSale(eq(id), eq(name))).thenReturn(updated);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/point-of-sale/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(name)))
                .andExpect(status().isOk());
    }

    @Test
    void controllerTestDeletePointOfSale() throws Exception {
        doNothing().when(pointOfSaleService).deletePointOfSale(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/point-of-sale/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void deletePointOfSale_shouldReturnNotFound_whenPointOfSaleDoesntExist() throws Exception {
        int id = 99;

        doThrow(new PointOfSaleNotFoundException(id)).when(pointOfSaleService).deletePointOfSale(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/point-of-sale/{id}", id))
                .andExpect(status().isNotFound());
    }
}