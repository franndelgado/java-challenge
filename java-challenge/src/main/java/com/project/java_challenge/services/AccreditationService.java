package com.project.java_challenge.services;

 import com.project.java_challenge.dtos.AccreditationDTO;
 import com.project.java_challenge.dtos.AccreditationResponseDTO;
 import com.project.java_challenge.dtos.PointOfSale;
 import com.project.java_challenge.entities.Accreditation;
 import com.project.java_challenge.repositories.AccreditationRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.time.LocalDate;
 import java.util.List;
 import java.util.NoSuchElementException;

@Service
public class AccreditationService {

    private final AccreditationRepository accreditationRepository;

    public AccreditationService(AccreditationRepository accreditationRepository) {
        this.accreditationRepository = accreditationRepository;
    }

    private final List<PointOfSale> pointOfSaleList = List.of(
        new PointOfSale(1, "CABA"),
        new PointOfSale(2, "GBA_1"),
        new PointOfSale(3, "GBA_2"),
        new PointOfSale(4, "Santa Fe"),
        new PointOfSale(5, "Córdoba"),
        new PointOfSale(6, "Misiones"),
        new PointOfSale(7, "Salta"),
        new PointOfSale(8, "Chubut"),
        new PointOfSale(9, "Santa Cruz"),
        new PointOfSale(10, "Catamarca")
    );

    /***
     * POST Method:
     * This method receives an AccreditationDTO and finds the Point of Sale with the Identifier, creates the Accreditation
     * saves it in the Data Base and then returns the new Accreditation.
     * If the point of sale doesn't exist throws an Illegal Argument Exception.
     * @param accreditationDTO
     * @return AccreditationResponseDTO
     */
    public AccreditationResponseDTO processAccreditation(AccreditationDTO accreditationDTO) {

        if(accreditationDTO.getPointOfSaleId() == null || accreditationDTO.getAmount() == null) {
            throw new IllegalArgumentException("Point of sale identifier and Amount cannot be null.");
        }

        PointOfSale pointOfSale = pointOfSaleList.stream()
                .filter(pos -> pos.getId() == accreditationDTO.getPointOfSaleId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Point of sale does not exist."));

        Accreditation newAccreditation = new Accreditation();
        newAccreditation.setPointOfSaleId(accreditationDTO.getPointOfSaleId());
        newAccreditation.setAmount(accreditationDTO.getAmount());
        newAccreditation.setDate(LocalDate.now());
        newAccreditation.setPointOfSaleName(pointOfSale.getName());

        Accreditation accreditationSaved = accreditationRepository.save(newAccreditation);

        return new AccreditationResponseDTO(
                accreditationSaved.getPointOfSaleId(),
                accreditationSaved.getAmount(),
                accreditationSaved.getDate(),
                accreditationSaved.getPointOfSaleName()
        );
    }
}