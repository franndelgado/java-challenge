package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.AccreditationDTO;
import com.project.java_challenge.dtos.AccreditationResponseDTO;
import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.models.Accreditation;
import com.project.java_challenge.repositories.AccreditationRepository;
import com.project.java_challenge.services.AccreditationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccreditationServiceImplTest {

    @Mock
    private AccreditationRepository accreditationRepository;

    @InjectMocks
    private AccreditationServiceImpl accreditationService;

    @Test
    void processAccreditationSuccessfully() throws InvalidAccreditationRequestBodyException {

        AccreditationDTO accreditationDTO = new AccreditationDTO(1, 500L);
        Accreditation expectedAccreditation = new Accreditation();
        expectedAccreditation.setPointOfSaleId(1);
        expectedAccreditation.setAmount(500L);
        expectedAccreditation.setReceptionDate(LocalDate.now());
        expectedAccreditation.setPointOfSaleName("CABA");

        when(accreditationRepository.save(any(Accreditation.class))).thenReturn(expectedAccreditation);

        AccreditationResponseDTO response = accreditationService.processAccreditation(accreditationDTO);

        assertEquals(1, response.getPointOfSaleId());
        assertEquals(500L, response.getAmount());
        assertEquals("CABA", response.getPointOfSaleName());
        assertNotNull(response.getDate());
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

        assertThrows(NoSuchElementException.class, () -> accreditationService.processAccreditation(accreditationDTO));
    }

    @Test
    void shouldReturnListOfAllAccreditations() {
        List<Accreditation> mockList = List.of(new Accreditation(1500L, 1, "CABA", LocalDate.now()));
        when(accreditationRepository.findAll()).thenReturn(mockList);

        List<Accreditation> result = accreditationService.getAllAccreditations();

        assertEquals(1, result.size());
        assertEquals(mockList.get(0), result.get(0));
    }
}
