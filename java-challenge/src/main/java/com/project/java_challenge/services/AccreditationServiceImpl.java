package com.project.java_challenge.services;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.repositories.AccreditationRepository;

import com.project.java_challenge.repositories.PointOfSaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccreditationServiceImpl implements AccreditationService {

    private final AccreditationRepository accreditationRepository;
    private final PointOfSaleRepository pointOfSaleRepository;

    public AccreditationServiceImpl(AccreditationRepository accreditationRepository, PointOfSaleRepository pointOfSaleRepository) {
        this.accreditationRepository = accreditationRepository;
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    /***
     * POST Method:
     * This method receives an AccreditationDTO and finds the Point of Sale with the Identifier, creates the Accreditation
     * saves it in the Data Base and then returns the new Accreditation.
     * If the point of sale doesn't exist throws an Illegal Argument Exception.
     * @param accreditationDTO
     * @return AccreditationResponseDTO
     */
    @Override
    public AccreditationResponseDTO processAccreditation(AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException, PointOfSaleNotFoundException {

        Integer idPointOfSale = Optional.ofNullable(accreditationDTO.getPointOfSaleId())
                .orElseThrow(() -> new InvalidAccreditationRequestBodyException("Point Of Sale Identifier cannot be null."));
        Optional.ofNullable(accreditationDTO.getAmount())
                .orElseThrow(() -> new InvalidAccreditationRequestBodyException("Amount cannot be null."));

        PointOfSale pointOfSale = pointOfSaleRepository.findById(idPointOfSale)
                .orElseThrow(() -> new PointOfSaleNotFoundException(accreditationDTO.getPointOfSaleId()));

        Accreditation newAccreditation = new Accreditation();
        newAccreditation.setIdPointOfSale(accreditationDTO.getPointOfSaleId());
        newAccreditation.setAmount(Double.valueOf(accreditationDTO.getAmount()));
        newAccreditation.setReceptionDate(LocalDateTime.now());
        newAccreditation.setPointOfSaleName(pointOfSale.getName());

        Accreditation accreditationSaved = accreditationRepository.save(newAccreditation);

        return new AccreditationResponseDTO(
                accreditationSaved.getIdPointOfSale(),
                accreditationSaved.getAmount(),
                accreditationSaved.getReceptionDate(),
                accreditationSaved.getPointOfSaleName()
        );
    }

    /**
     * <b>GET Method:</b><br>
     * This method gets the List of accreditations and returns it.
     *
     * @return List<Accreditation>
     */
    @Override
    public List<Accreditation> getAllAccreditations(){
        return accreditationRepository.findAll();
    }
}
