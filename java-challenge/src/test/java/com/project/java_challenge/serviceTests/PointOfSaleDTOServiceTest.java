package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.entities.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class PointOfSaleDTOServiceTest {

    private PointOfSaleService pointOfSaleService;
    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    @BeforeEach
    void setUp() {
        pointOfSaleService = new PointOfSaleService(pointOfSaleRepository);
    }

    @Test
    void testGetPointOfSale() {
        when(pointOfSaleRepository.findAll()).thenReturn(List.of(new PointOfSale()));
    }

    @Test
    void testAddPointOfSale() {

        PointOfSaleDTO newPointOfSaleDTO = new PointOfSaleDTO(11, "Chaco");
        pointOfSaleService.createNewPointOfSale(newPointOfSaleDTO);
        List<PointOfSale> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assertThat(pointOfSaleDTOList1.size()).isEqualTo(11);
        assert(pointOfSaleDTOList1).contains(newPointOfSaleDTO);
    }

    @Test
    void testUpdatePointOfSale() {

        PointOfSaleDTO newPointOfSaleDTO = new PointOfSaleDTO(1, "Chaco");
        pointOfSaleService.updatePointOfSale(newPointOfSaleDTO);
        List<PointOfSale> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleDTOList1).stream().anyMatch(p -> p.getId() == newPointOfSaleDTO.getId() && p.getName().equals(newPointOfSaleDTO.getName()));
    }

    @Test
    void testDeletePointOfSale() {

        pointOfSaleService.deletePointOfSale(1);
        List<PointOfSale> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleDTOList1).stream().noneMatch(pointOfSale -> pointOfSale.getId() == 1);
    }
}
