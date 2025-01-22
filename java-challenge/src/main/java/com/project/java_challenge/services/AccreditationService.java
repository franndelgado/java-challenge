package com.project.java_challenge.services;

 import com.project.java_challenge.dtos.AccreditationResponseDTO;
 import com.project.java_challenge.dtos.PointOfSale;
 import com.project.java_challenge.entities.Accreditation;
 import com.project.java_challenge.repositories.AccreditationRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.time.LocalDate;
 import java.util.List;

@Service
public class AccreditationService {

    private final AccreditationRepository accreditationRepository;

    @Autowired
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
    
    public AccreditationResponseDTO processAccreditation(int pointOfSaleId, Long amount) {

        PointOfSale pointOfSale = pointOfSaleList.stream()
                .filter(pos -> pos.getId() == pointOfSaleId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Point of sale does not exist."));

        Accreditation newAccreditation = new Accreditation();
        newAccreditation.setPointOfSaleId(pointOfSaleId);
        newAccreditation.setAmount(amount);
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

    public void testDataBase(){
        Accreditation accreditation = new Accreditation();
        accreditation.setId(3);
        accreditation.setPointOfSaleId(3);
        accreditation.setAmount(4000L);
        accreditation.setPointOfSaleName("Chaco");
        accreditation.setDate(LocalDate.now());

        accreditationRepository.save(accreditation);
        System.out.println();
    }
}