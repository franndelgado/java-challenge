package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.entities.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PointOfSaleDTOServiceTest {

    @InjectMocks
    private PointOfSaleService pointOfSaleService;
    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    private PointOfSaleDTO pointOfSaleDTO;
    private PointOfSale pointOfSale;

    @BeforeEach
    void setUp() {
        pointOfSaleService = new PointOfSaleService(pointOfSaleRepository);

        pointOfSaleDTO = new PointOfSaleDTO(1, "Chaco");

        pointOfSale = new PointOfSale();
        pointOfSale.setId(1);
        pointOfSale.setName("CABA");
    }

    @Test
    void testGetPointOfSale() {
        when(pointOfSaleRepository.findAll()).thenReturn(List.of(pointOfSale));
        pointOfSaleService.getPointOfSale();

        verify(pointOfSaleRepository).findAll();
    }

    @Test
    void testAddPointOfSale() {

        when(pointOfSaleRepository.save(any(PointOfSale.class))).thenReturn(pointOfSale);

        pointOfSaleService.createNewPointOfSale(pointOfSaleDTO);
        verify(pointOfSaleRepository).save(any(PointOfSale.class));
    }


    @Test
    void testUpdatePointOfSale() {

        when(pointOfSaleRepository.findById(1)).thenReturn(Optional.of(pointOfSale));
        when(pointOfSaleRepository.save(any(PointOfSale.class))).thenReturn(pointOfSale);

        pointOfSaleService.updatePointOfSale(pointOfSaleDTO);
        verify(pointOfSaleRepository).findById(1);
        verify(pointOfSaleRepository).save(any(PointOfSale.class));
    }


    @Test
    void testDeletePointOfSale() {

        when(pointOfSaleRepository.existsById(1)).thenReturn(true);
        doNothing().when(pointOfSaleRepository).deleteById(1);

        pointOfSaleService.deletePointOfSale(1);

        verify(pointOfSaleRepository).deleteById(1);
    }
}
