package com.project.java_challenge.controllers;
/**
 import com.project.java_challenge.entities.Accreditation;
 import com.project.java_challenge.services.AccreditationService;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 @RestController
 @RequestMapping("/accreditations")
 public class AccreditationController {

    private final AccreditationService accreditationService;

    public AccreditationController(AccreditationService accreditationService) {
        this.accreditationService = accreditationService;
    }


    @PostMapping
    public ResponseEntity<AccreditationResponseDTO> addNewAccount(AccreditationDTO accreditationDTO) {
        return new ResponseEntity<>(new AccreditationResponseDTO(accreditationDTO), HttpStatus.CREATED);
    }


    @PostMapping
    public Accreditation saveAccreditation(Accreditation accreditation) {
        return accreditationService.saveAccreditation(accreditation);
    }
}
 */
