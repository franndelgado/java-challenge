package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PointOfSaleDTOServiceTest {

    private PointOfSaleService pointOfSaleService;

    @BeforeEach
    void setUp() {
        pointOfSaleService = new PointOfSaleService();
    }

    @Test
    void testGetPointOfSale() {

        List<PointOfSaleDTO> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assertThat(pointOfSaleDTOList1).isNotNull();
        assertThat(pointOfSaleDTOList1.size()).isEqualTo(10);
    }

    @Test
    void testAddPointOfSale() {

        PointOfSaleDTO newPointOfSaleDTO = new PointOfSaleDTO(11, "Chaco");
        pointOfSaleService.createNewPointOfSale(newPointOfSaleDTO);
        List<PointOfSaleDTO> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assertThat(pointOfSaleDTOList1.size()).isEqualTo(11);
        assert(pointOfSaleDTOList1).contains(newPointOfSaleDTO);
    }

    @Test
    void testUpdatePointOfSale() {

        PointOfSaleDTO newPointOfSaleDTO = new PointOfSaleDTO(1, "Chaco");
        pointOfSaleService.updatePointOfSale(newPointOfSaleDTO);
        List<PointOfSaleDTO> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleDTOList1).stream().anyMatch(p -> p.getId() == newPointOfSaleDTO.getId() && p.getName().equals(newPointOfSaleDTO.getName()));
    }

    @Test
    void testDeletePointOfSale() {

        pointOfSaleService.deletePointOfSale(1);
        List<PointOfSaleDTO> pointOfSaleDTOList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleDTOList1).stream().noneMatch(pointOfSale -> pointOfSale.getId() == 1);
    }
}
