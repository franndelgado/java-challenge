package com.project.java_challenge.controllers;

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

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        accreditationService.testDataBase();
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AccreditationResponseDTO> addNewAccreditation(int pointOfSaleId, Long amount) {

        return ResponseEntity.status(HttpStatus.CREATED).body(accreditationService.processAccreditation(pointOfSaleId, amount));
    }
}
