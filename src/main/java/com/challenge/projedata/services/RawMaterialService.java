package com.challenge.projedata.services;

import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.exceptions.BadRequestException;
import com.challenge.projedata.exceptions.ResourceNotFoundException;
import com.challenge.projedata.repositories.ProductRepository;
import com.challenge.projedata.repositories.RawMaterialRepository;
import com.challenge.projedata.validations.RawMaterialValidation;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {
    RawMaterialRepository rawMaterialRepository;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository, ProductRepository productRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<RawMaterial> findAll(Sort sort) {
        return rawMaterialRepository.findAll(sort);
    }

    public RawMaterial findById(Integer id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Raw Material not found with id: " + id));
    }

    public RawMaterial save(RawMaterial rawMaterial) {
        RawMaterialValidation.validate(rawMaterial);

        rawMaterial.setName(rawMaterial.getName().trim());

        if (rawMaterialRepository.existsByNameIgnoreCase(rawMaterial.getName())) {
            throw new BadRequestException(
                    "A raw material with this name already exists"
            );
        }

        return rawMaterialRepository.save(rawMaterial);
    }

    public RawMaterial update(Integer id, RawMaterial updated) {
        RawMaterial entity = findById(id);

        RawMaterialValidation.validate(updated);

        updated.setName(updated.getName().trim());

        if (rawMaterialRepository.existsByNameIgnoreCaseAndIdNot(updated.getName(), id)) {
            throw new BadRequestException(
                    "A raw material with this name already exists."
            );
        }

        entity.setName(updated.getName());
        entity.setStockQuantity(updated.getStockQuantity());

        return rawMaterialRepository.save(entity);
    }

    public void delete(Integer id) {
        findById(id);
        rawMaterialRepository.deleteById(id);
    }
}
