package com.challenge.projedata.services;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.exceptions.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRawMaterialServiceTest {

    @Mock
    private ProductRawMaterialRepository productRawMaterialRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private ProductRawMaterialService productRawMaterialService;

    private Product product;
    private RawMaterial rawMaterial;

    @BeforeEach
    void setup() {
        product = new Product(1, "Product A", new BigDecimal("100"));
        rawMaterial = new RawMaterial(1, "Material A", 50);
    }

    @Test
    void shouldSaveAssociationSuccessfully() {

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.of(rawMaterial));

        when(productRawMaterialRepository.save(any(ProductRawMaterial.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductRawMaterial result =
                productRawMaterialService.save(1, 1, 10);

        assertNotNull(result);
        assertEquals(10, result.getRequiredQuantity());
        assertEquals(product, result.getProduct());
        assertEquals(rawMaterial, result.getRawMaterial());
    }

    @Test
    void shouldThrowWhenProductNotFound() {

        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productRawMaterialService.save(1, 1, 10);
        });

        verify(productRawMaterialRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenRawMaterialNotFound() {

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productRawMaterialService.save(1, 1, 10);
        });

        verify(productRawMaterialRepository, never()).save(any());
    }

    @Test
    void shouldFindByProductId() {

        ProductRawMaterial relation =
                new ProductRawMaterial(1, product, rawMaterial, 5);

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(productRawMaterialRepository.findByProduct_Id(1))
                .thenReturn(List.of(relation));

        List<ProductRawMaterial> result =
                productRawMaterialService.findByProductId(1);

        assertEquals(1, result.size());
    }

    @Test
    void shouldUpdateAssociationSuccessfully() {

        ProductRawMaterial relation =
                new ProductRawMaterial(1, product, rawMaterial, 5);

        when(productRawMaterialRepository.findById(1))
                .thenReturn(Optional.of(relation));

        when(productRawMaterialRepository.save(any(ProductRawMaterial.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductRawMaterial updated =
                productRawMaterialService.update(1, 20);

        assertEquals(20, updated.getRequiredQuantity());
    }

    @Test
    void shouldDeleteAssociationSuccessfully() {

        productRawMaterialService.delete(1);

        verify(productRawMaterialRepository, times(1))
                .deleteById(1);
    }
}