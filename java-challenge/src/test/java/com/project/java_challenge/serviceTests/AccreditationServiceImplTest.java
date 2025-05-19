package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.repositories.AccreditationRepository;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.AccreditationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccreditationServiceImplTest {

    @Mock
    private AccreditationRepository accreditationRepository;

    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    @InjectMocks
    private AccreditationServiceImpl accreditationService;

    @Test
    void processAccreditationSuccessfully() throws InvalidAccreditationRequestBodyException, PointOfSaleNotFoundException {

        AccreditationDTO accreditationDTO = new AccreditationDTO(1, 500L);

        PointOfSale mockPointOfSale = new PointOfSale();
        mockPointOfSale.setId(1);
        mockPointOfSale.setName("CABA");

        when(pointOfSaleRepository.findById(1)).thenReturn(Optional.of(mockPointOfSale));

        Accreditation expectedAccreditation = new Accreditation();
        expectedAccreditation.setIdPointOfSale(1);
        expectedAccreditation.setAmount(500.00);
        expectedAccreditation.setReceptionDate(LocalDateTime.of(2025, 5, 19, 10, 0));
        expectedAccreditation.setPointOfSaleName("CABA");

        when(accreditationRepository.save(any(Accreditation.class))).thenReturn(expectedAccreditation);

        AccreditationResponseDTO response = accreditationService.processAccreditation(accreditationDTO);

        assertEquals(1, response.getIdPointOfSale());
        assertEquals(500.00, response.getAmount());
        assertEquals("CABA", response.getPointOfSaleName());
    }

    @Test
    void processAccreditationFailWhenPointOfSaleIdIsNull() {
        AccreditationDTO accreditationDTO = new AccreditationDTO(null, 500L);

        assertThrows(InvalidAccreditationRequestBodyException.class, () -> accreditationService.processAccreditation(accreditationDTO));
    }

    @Test
    void processAccreditationFailWhenAmountIsNull() {
        AccreditationDTO accreditationDTO = new AccreditationDTO(1, null);

        assertThrows(InvalidAccreditationRequestBodyException.class, () -> accreditationService.processAccreditation(accreditationDTO));
    }

    @Test
    void processAccreditationFailWhenPointOfSaleIdNotFound() {
        AccreditationDTO accreditationDTO = new AccreditationDTO(99, 500L);

        assertThrows(PointOfSaleNotFoundException.class, () -> accreditationService.processAccreditation(accreditationDTO));
    }

    @Test
    void shouldReturnListOfAllAccreditations() {
        List<Accreditation> mockList = List.of(new Accreditation("12342", 500.00, 1, LocalDateTime.now(), "CABA"));
        when(accreditationRepository.findAll()).thenReturn(mockList);

        List<Accreditation> result = accreditationService.getAllAccreditations();

        assertEquals(1, result.size());
        assertEquals(mockList.get(0), result.get(0));
    }
}
