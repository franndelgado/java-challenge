package com.project.java_challenge.api;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.models.Accreditation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface AccreditationApi {

    @Operation(summary = "Crear una nueva acreditación",
            description = "Crea una nueva acreditación con la fecha y el nombre del punto de venta.")
    @ResponseBody ResponseEntity<AccreditationResponseDTO> addNewAccreditation(@RequestBody AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException;

    @Operation(summary = "Obtener todas las acreditaciones",
            description = "Obtiene una lista de todas las acreditaciones con su información.")
    @ResponseBody ResponseEntity<List<Accreditation>> getAllAccreditations();
}
