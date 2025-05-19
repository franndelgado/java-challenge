package com.project.java_challenge.controllers;

import com.project.java_challenge.api.AccreditationApi;
import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.services.AccreditationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accreditations")
public class AccreditationController implements AccreditationApi {

    private final AccreditationServiceImpl accreditationServiceImpl;

    public AccreditationController(AccreditationServiceImpl accreditationServiceImpl) {
        this.accreditationServiceImpl = accreditationServiceImpl;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AccreditationResponseDTO> addNewAccreditation(@RequestBody AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException, PointOfSaleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(accreditationServiceImpl.processAccreditation(accreditationDTO));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Accreditation>> getAllAccreditations() {
        return ResponseEntity.ok(accreditationServiceImpl.getAllAccreditations());
    }
}
