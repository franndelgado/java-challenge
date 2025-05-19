package com.project.java_challenge.services;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.Accreditation;

import java.util.List;

public interface AccreditationService {
    AccreditationResponseDTO processAccreditation(AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException, PointOfSaleNotFoundException;
    List<Accreditation> getAllAccreditations();
}
