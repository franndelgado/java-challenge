package com.project.java_challenge.serviceTests;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;
import com.project.java_challenge.repositories.PointOfSaleRepository;
import com.project.java_challenge.services.PointOfSaleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class PointOfSaleServiceImplTest {

    @Mock
    private PointOfSaleRepository pointOfSaleRepository;

    @InjectMocks
    private PointOfSaleServiceImpl pointOfSaleService;

    @Test
    void shouldReturnListOfPointOfSales() {
        int page = 0;
        int size = 10;
        List<PointOfSale> mockList = IntStream.range(0, 10)
                .mapToObj(i -> new PointOfSale( i, "POS " + i))
                .collect(Collectors.toList());
        Page<PointOfSale> pageResult = new PageImpl<>(mockList);

        when(pointOfSaleRepository.findAll(PageRequest.of(page, size))).thenReturn(pageResult);

        List<PointOfSale> result = pointOfSaleService.getAllPointOfSale(page, size);

        assertEquals(10, result.size());
        assertEquals("POS 0", result.get(0).getName());
        verify(pointOfSaleRepository).findAll(PageRequest.of(page, size));
    }

    @Test
    void shouldCreateNewPointOfSale() {
        PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setName("Chaco");

        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setId(1);
        pointOfSale.setName("Chaco");

        when(pointOfSaleRepository.save(any(PointOfSale.class))).thenReturn(pointOfSale);

        PointOfSale result = pointOfSaleService.createNewPointOfSale(dto);

        assertNotNull(result);
        assertEquals("Chaco", result.getName());
    }

    @Test
    void shouldThrowErrorWhenCreateNullPointOfSale() {
        assertThrows(IllegalArgumentException.class, () -> pointOfSaleService.createNewPointOfSale(null));
    }

    @Test
    void shouldThrowErrorWhenCreatePointOfSaleWithEmptyName() {
        PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setName("");

        assertThrows(IllegalArgumentException.class, () -> pointOfSaleService.createNewPointOfSale(dto));
    }

    @Test
    void shouldUpdatePointOfSale() throws PointOfSaleNotFoundException {
        PointOfSale existingPointOfSale = new PointOfSale();
        existingPointOfSale.setId(1);
        existingPointOfSale.setName("Chaco");

        when(pointOfSaleRepository.findById(1)).thenReturn(Optional.of(existingPointOfSale));
        when(pointOfSaleRepository.save(any(PointOfSale.class))).thenAnswer(invocation -> invocation.getArgument(0));

        PointOfSale result = pointOfSaleService.updatePointOfSale(1, "Nuevo Chaco");

        assertEquals("Nuevo Chaco", result.getName());
    }

    @Test
    void shouldThrowErrorWhenPointOfSaleNotFoundForUpdate() {
        when(pointOfSaleRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(PointOfSaleNotFoundException.class, () -> pointOfSaleService.updatePointOfSale(99, "Nuevo Chaco"));
    }

    @Test
    void shouldDeletePointOfSale() throws PointOfSaleNotFoundException {
        when(pointOfSaleRepository.existsById(1)).thenReturn(true);

        pointOfSaleService.deletePointOfSale(1);

        verify(pointOfSaleRepository).deleteById(1);
    }

    @Test
    void shouldThrowErrorWhenPointOfSaleNotFoundForDelete() {
        when(pointOfSaleRepository.existsById(99)).thenReturn(false);

        assertThrows(PointOfSaleNotFoundException.class, () -> pointOfSaleService.deletePointOfSale(99));
    }
}
