package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSale;
import com.project.java_challenge.services.PointOfSaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PointOfSaleServiceTest {

    private PointOfSaleService pointOfSaleService;

    @BeforeEach
    void setUp() {
        pointOfSaleService = new PointOfSaleService();
    }

    @Test
    void testGetPointOfSale() {

        List<PointOfSale> pointOfSaleList1 = pointOfSaleService.getPointOfSale();
        assertThat(pointOfSaleList1).isNotNull();
        assertThat(pointOfSaleList1.size()).isEqualTo(10);
    }

    @Test
    void testAddPointOfSale() {

        PointOfSale newPointOfSale = new PointOfSale(11, "Chaco");
        pointOfSaleService.createNewPointOfSale(newPointOfSale);
        List<PointOfSale> pointOfSaleList1 = pointOfSaleService.getPointOfSale();
        assertThat(pointOfSaleList1.size()).isEqualTo(11);
        assert(pointOfSaleList1).contains(newPointOfSale);
    }

    @Test
    void testUpdatePointOfSale() {

        PointOfSale newPointOfSale = new PointOfSale(1, "Chaco");
        pointOfSaleService.updatePointOfSale(newPointOfSale);
        List<PointOfSale> pointOfSaleList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleList1).stream().anyMatch(p -> p.getId() == newPointOfSale.getId() && p.getName().equals(newPointOfSale.getName()));
    }

    @Test
    void testDeletePointOfSale() {

        pointOfSaleService.deletePointOfSale(1);
        List<PointOfSale> pointOfSaleList1 = pointOfSaleService.getPointOfSale();
        assert(pointOfSaleList1).stream().noneMatch(pointOfSale -> pointOfSale.getId() == 1);
    }
}
