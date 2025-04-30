package com.project.java_challenge.services;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.repositories.AccreditationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccreditationServiceImpl implements AccreditationService {

    private final AccreditationRepository accreditationRepository;

    public AccreditationServiceImpl(AccreditationRepository accreditationRepository) {
        this.accreditationRepository = accreditationRepository;
    }

    private final List<PointOfSaleDTO> pointOfSaleDTOList = List.of(
            new PointOfSaleDTO(1, "CABA"),
            new PointOfSaleDTO(2, "GBA_1"),
            new PointOfSaleDTO(3, "GBA_2"),
            new PointOfSaleDTO(4, "Santa Fe"),
            new PointOfSaleDTO(5, "Córdoba"),
            new PointOfSaleDTO(6, "Misiones"),
            new PointOfSaleDTO(7, "Salta"),
            new PointOfSaleDTO(8, "Chubut"),
            new PointOfSaleDTO(9, "Santa Cruz"),
            new PointOfSaleDTO(10, "Catamarca")
    );

    /***
     * POST Method:
     * This method receives an AccreditationDTO and finds the Point of Sale with the Identifier, creates the Accreditation
     * saves it in the Data Base and then returns the new Accreditation.
     * If the point of sale doesn't exist throws an Illegal Argument Exception.
     * @param accreditationDTO
     * @return AccreditationResponseDTO
     */
    @Override
    public AccreditationResponseDTO processAccreditation(AccreditationDTO accreditationDTO) throws InvalidAccreditationRequestBodyException {

        Optional.ofNullable(accreditationDTO.getPointOfSaleId())
                .orElseThrow(() -> new InvalidAccreditationRequestBodyException("Point Of Sale Identifier cannot be null."));
        Optional.ofNullable(accreditationDTO.getAmount())
                .orElseThrow(() -> new InvalidAccreditationRequestBodyException("Amount cannot be null."));

        PointOfSaleDTO pointOfSaleDTO = pointOfSaleDTOList.stream()
                .filter(pos -> pos.getId() == accreditationDTO.getPointOfSaleId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Point of sale does not exist."));

        Accreditation newAccreditation = new Accreditation();
        newAccreditation.setPointOfSaleId(accreditationDTO.getPointOfSaleId());
        newAccreditation.setAmount(accreditationDTO.getAmount());
        newAccreditation.setReceptionDate(LocalDate.now());
        newAccreditation.setPointOfSaleName(pointOfSaleDTO.getName());

        Accreditation accreditationSaved = accreditationRepository.save(newAccreditation);

        return new AccreditationResponseDTO(
                accreditationSaved.getPointOfSaleId(),
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