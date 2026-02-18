package com.challenge.projedata.services;

import com.challenge.projedata.dtos.ProductionResponseDTO;
import com.challenge.projedata.entities.Product;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.repositories.ProductRawMaterialRepository;
import com.challenge.projedata.repositories.ProductRepository;
import com.challenge.projedata.repositories.RawMaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductionServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductRawMaterialRepository productRawMaterialRepository;

    @InjectMocks
    private ProductionService productionService;

    private Product productA;
    private Product productB;
    private RawMaterial rawMaterial;

    @BeforeEach
    void setUp() {
        productA = new Product(1, "Product A", new BigDecimal("150"));
        productB = new Product(2, "Product B", new BigDecimal("200"));

        rawMaterial = new RawMaterial(1, "Material A", 50);
    }

    @Test
    void shouldCalculateProductionCorrectlyAndSortByHigherValue() {
        ProductRawMaterial relation1 = new ProductRawMaterial(1, productA, rawMaterial, 10);

        ProductRawMaterial relation2 = new ProductRawMaterial(1, productB, rawMaterial, 25);

        when(productRepository.findAll()).thenReturn(List.of(productA, productB));

        when(productRawMaterialRepository.findByProduct_Id(1)).thenReturn(List.of(relation1));

        when(productRawMaterialRepository.findByProduct_Id(2)).thenReturn(List.of(relation2));

        List<ProductionResponseDTO> result = productionService.calculateProduction();

        assertEquals(2, result.size());

        assertEquals("Product B", result.get(0).getProductName());
        assertEquals(2, result.get(0).getPossibleQuantity());

        assertEquals("Product A", result.get(1).getProductName());
        assertEquals(5, result.get(1).getPossibleQuantity());
    }

    @Test
    void shouldReturnEmptyListWhenNoStockAvailable() {
        RawMaterial noStockMaterial = new RawMaterial(1, "Material A", 0);

        ProductRawMaterial relation = new ProductRawMaterial(1,productA, noStockMaterial, 10);

        when(productRepository.findAll()).thenReturn(List.of(productA));

        when(productRawMaterialRepository.findByProduct_Id(1)).thenReturn(List.of(relation));

        List<ProductionResponseDTO> result = productionService.calculateProduction();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldIgnoreProductWithoutRelations() {
        when(productRepository.findAll()).thenReturn(List.of(productA));

        when(productRawMaterialRepository.findByProduct_Id(1)).thenReturn(List.of());

        List<ProductionResponseDTO> result = productionService.calculateProduction();

        assertTrue(result.isEmpty());
    }
}
