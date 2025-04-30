package com.project.java_challenge.services;

 import com.project.java_challenge.dtos.AccreditationDTO;
 import com.project.java_challenge.dtos.AccreditationResponseDTO;
 import java.util.List;
 import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
 import com.project.java_challenge.models.Accreditation;


public interface AccreditationService {
    AccreditationResponseDTO processAccreditation(AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException;
    List<Accreditation> getAllAccreditations();
}