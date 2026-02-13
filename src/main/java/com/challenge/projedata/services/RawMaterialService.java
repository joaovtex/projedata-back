package com.challenge.projedata.services;

import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.repositories.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {
    RawMaterialRepository rawMaterialRepository;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<RawMaterial> findAll() {
        return rawMaterialRepository.findAll();
    }

    public RawMaterial findById(Integer id) {
        return rawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria Prima não encontrada."));
    }

    public RawMaterial save(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public RawMaterial update(Integer id, RawMaterial updated) {
        RawMaterial entity = findById(id);

        entity.setName(updated.getName());
        entity.setStockQuantity(updated.getStockQuantity());

        return rawMaterialRepository.save(entity);
    }

    public void delete(Integer id) {
        rawMaterialRepository.deleteById(id);
    }
}
