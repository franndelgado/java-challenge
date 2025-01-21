package com.project.java_challenge.services;

/**
 import com.project.java_challenge.dtos.AccreditationDTO;
 import com.project.java_challenge.dtos.AccreditationResponseDTO;
 import com.project.java_challenge.dtos.PointOfSale;
 import com.project.java_challenge.entities.Accreditation;
 import com.project.java_challenge.repositories.AccreditationRepository;
 import org.springframework.stereotype.Service;

 import java.time.LocalDate;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Optional;

@Service
public class AccreditationService {

    private AccreditationRepository accreditationRepository;
    private final List<PointOfSale> pointOfSaleList = new ArrayList<>();

    public AccreditationService(AccreditationRepository accreditationRepository) {
        this.accreditationRepository = accreditationRepository;

        pointOfSaleList.add(new PointOfSale(1, "CABA"));
        pointOfSaleList.add(new PointOfSale(2, "GBA_1"));
        pointOfSaleList.add(new PointOfSale(3, "GBA_2"));
        pointOfSaleList.add(new PointOfSale(4, "Santa Fe"));
        pointOfSaleList.add(new PointOfSale(5, "Córdoba"));
        pointOfSaleList.add(new PointOfSale(6, "Misiones"));
        pointOfSaleList.add(new PointOfSale(7, "Salta"));
        pointOfSaleList.add(new PointOfSale(8, "Chubut"));
        pointOfSaleList.add(new PointOfSale(9, "Santa Cruz"));
        pointOfSaleList.add(new PointOfSale(10, "Catamarca"));
    }

    public Accreditation saveAccreditation(Accreditation accreditation) {
        return accreditationRepository.save(accreditation);
    }
}
*/