package com.challenge.projedata.services;


import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.exceptions.BadRequestException;
import com.challenge.projedata.exceptions.ResourceNotFoundException;
import com.challenge.projedata.repositories.RawMaterialRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RawMaterialServiceTest {

    @Mock
    private RawMaterialRepository rawMaterialRepository;

    @InjectMocks
    private RawMaterialService rawMaterialService;

    private RawMaterial rawMaterial;

    @BeforeEach
    void setup() {
        rawMaterial = new RawMaterial(1, "Material A", 100);
    }

    @Test
    void shouldSaveRawMaterialSuccessfully() {

        when(rawMaterialRepository.existsByNameIgnoreCase("Material A"))
                .thenReturn(false);

        when(rawMaterialRepository.save(rawMaterial))
                .thenReturn(rawMaterial);

        RawMaterial saved = rawMaterialService.save(rawMaterial);

        assertNotNull(saved);
        assertEquals("Material A", saved.getName());

        verify(rawMaterialRepository, times(1)).save(rawMaterial);
    }

    @Test
    void shouldThrowExceptionWhenNameAlreadyExists() {

        when(rawMaterialRepository.existsByNameIgnoreCase("Material A"))
                .thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            rawMaterialService.save(rawMaterial);
        });

        verify(rawMaterialRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenStockIsNegative() {

        RawMaterial invalid = new RawMaterial(1, "Invalid", -10);

        assertThrows(BadRequestException.class, () -> {
            rawMaterialService.save(invalid);
        });
    }

    @Test
    void shouldFindRawMaterialById() {

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.of(rawMaterial));

        RawMaterial found = rawMaterialService.findById(1);

        assertEquals("Material A", found.getName());
    }

    @Test
    void shouldThrowWhenRawMaterialNotFound() {

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            rawMaterialService.findById(1);
        });
    }

    @Test
    void shouldUpdateRawMaterialSuccessfully() {

        RawMaterial updated = new RawMaterial(null, "Updated", 200);

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.of(rawMaterial));

        when(rawMaterialRepository.existsByNameIgnoreCaseAndIdNot("Updated", 1))
                .thenReturn(false);

        when(rawMaterialRepository.save(any(RawMaterial.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RawMaterial result = rawMaterialService.update(1, updated);

        assertEquals("Updated", result.getName());
        assertEquals(200, result.getStockQuantity());
    }

    @Test
    void shouldDeleteRawMaterialSuccessfully() {

        when(rawMaterialRepository.findById(1))
                .thenReturn(Optional.of(rawMaterial));

        rawMaterialService.delete(1);

        verify(rawMaterialRepository, times(1)).deleteById(1);
    }
}
