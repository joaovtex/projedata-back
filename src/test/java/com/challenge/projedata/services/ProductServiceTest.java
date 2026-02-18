package com.challenge.projedata.services;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.exceptions.BadRequestException;
import com.challenge.projedata.exceptions.ResourceNotFoundException;
import com.challenge.projedata.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(1, "Product A", new BigDecimal("100"));
    }

    @Test
    void shouldSaveProductSuccessfully() {
        when(productRepository.existsByNameIgnoreCase("Product A")).thenReturn(false);

        when(productRepository.save(product)).thenReturn(product);

        Product saved = productService.save(product);

        assertNotNull(saved);
        assertEquals("Product A", saved.getName());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExists() {
        when(productRepository.existsByNameIgnoreCase("Product A")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            productService.save(product);
        });
    }

    @Test
    void shouldThrowExceptionWhenValueIsZeroOrNegative() {
        Product invalid = new Product(1, "Invalid", BigDecimal.ZERO);

        assertThrows(BadRequestException.class, () -> {
            productService.save(invalid);
        });
    }

    @Test
    void shouldFindProductById() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product found = productService.findById(1);

        assertEquals("Product A", found.getName());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findById(1);
        });
    }

    @Test
    void shouldUpdateProductSuccessfully() {
        Product updated = new Product(null, "Updated", new BigDecimal("150"));

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        when(productRepository.existsByNameIgnoreCaseAndIdNot("Updated", 1)).thenReturn(false);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.update(1, updated);

        assertEquals("Updated", result.getName());
        assertEquals(new BigDecimal("150"), result.getValue());
    }

    @Test
    void shouldDeleteProductSuccessfully() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        productService.delete(1);

        verify(productRepository, times(1)).deleteById(1);
    }
}
