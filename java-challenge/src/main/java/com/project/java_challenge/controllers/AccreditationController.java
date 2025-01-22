package com.project.java_challenge.controllers;

 import com.project.java_challenge.dtos.AccreditationDTO;
 import com.project.java_challenge.dtos.AccreditationResponseDTO;
 import com.project.java_challenge.services.AccreditationService;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accreditations")
public class AccreditationController {

    private final AccreditationService accreditationService;

    public AccreditationController(AccreditationService accreditationService) {
        this.accreditationService = accreditationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AccreditationResponseDTO> addNewAccreditation(@RequestBody AccreditationDTO accreditationDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accreditationService.processAccreditation(accreditationDTO));
    }
}
