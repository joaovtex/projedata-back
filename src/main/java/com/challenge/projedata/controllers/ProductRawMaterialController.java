package com.challenge.projedata.controllers;

import com.challenge.projedata.dtos.ProductRawMaterialRequestDTO;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.services.ProductRawMaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto-materia-prima")
public class ProductRawMaterialController {
    private final ProductRawMaterialService productRawMaterialService;

    public ProductRawMaterialController(ProductRawMaterialService productRawMaterialService) {
        this.productRawMaterialService = productRawMaterialService;
    }

    @GetMapping
    public List<ProductRawMaterial> findAll() {
        return productRawMaterialService.findAll();
    }

    @PostMapping
    public ProductRawMaterial create(@RequestBody ProductRawMaterialRequestDTO dto) {
        return productRawMaterialService.save(
                dto.getProductId(),
                dto.getRawMaterialId(),
                dto.getRequiredQuantity()
        );
    }

    @PutMapping("/{id}")
    public ProductRawMaterial update(@PathVariable Integer id, @RequestBody ProductRawMaterialRequestDTO dto) {
        return productRawMaterialService.update(id, dto.getRequiredQuantity());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productRawMaterialService.delete(id);
    }
}
