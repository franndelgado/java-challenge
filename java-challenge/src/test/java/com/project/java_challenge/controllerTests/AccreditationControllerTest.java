package com.project.java_challenge.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.java_challenge.controllers.AccreditationController;
import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.services.AccreditationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccreditationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AccreditationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccreditationServiceImpl accreditationService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccreditationDTO accreditationDTO;

    @Test
    void controllerTestProccessAccreditation() throws Exception {
        accreditationDTO = new AccreditationDTO(1, 500L);
        AccreditationResponseDTO response = new AccreditationResponseDTO(1, 500.0, LocalDateTime.now(), "CABA");

        when(accreditationService.processAccreditation(accreditationDTO)).thenReturn(response);

        mockMvc.perform(post("/api/accreditations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accreditationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllAccreditations() throws Exception {
        Accreditation accreditation = new Accreditation("38471093274", 500.0, 1, LocalDateTime.now(), "CABA");
        Accreditation accreditation2 = new Accreditation("43648739283", 2300.0, 4, LocalDateTime.now(), "Santa Fe");

        when(accreditationService.getAllAccreditations()).thenReturn(List.of(accreditation, accreditation2));

        mockMvc.perform(get("/api/accreditations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idPointOfSale").value(1L));
    }
}
