package com.project.java_challenge.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AccreditationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void controllerTestPostNewAccreditation() throws Exception {

        String requestBody = """
                {
                    "pointOfSaleId": 2,
                    "amount": 1000
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/accreditations")
                        .contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated());
    }
}
