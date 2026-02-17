package com.challenge.projedata.controllers;

import com.challenge.projedata.dtos.ProductRawMaterialRequestDTO;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.services.ProductRawMaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product-raw-material")
public class ProductRawMaterialController {
    private final ProductRawMaterialService productRawMaterialService;

    public ProductRawMaterialController(ProductRawMaterialService productRawMaterialService) {
        this.productRawMaterialService = productRawMaterialService;
    }

    @GetMapping
    public List<ProductRawMaterial> findAll() {
        return productRawMaterialService.findAll();
    }

    @GetMapping("/product={productId}")
    public List<ProductRawMaterial> findByProduct(@PathVariable Integer productId) {
        return productRawMaterialService.findByProductId(productId);
    }

    @PostMapping("/new")
    public ProductRawMaterial create(@RequestBody ProductRawMaterialRequestDTO dto) {
        return productRawMaterialService.save(
                dto.getProductId(),
                dto.getRawMaterialId(),
                dto.getRequiredQuantity()
        );
    }

    @PutMapping("/edit={id}")
    public ProductRawMaterial update(@PathVariable Integer id, @RequestBody ProductRawMaterialRequestDTO dto) {
        return productRawMaterialService.update(id, dto.getRequiredQuantity());
    }

    @DeleteMapping("/delete={id}")
    public void delete(@PathVariable Integer id) {
        productRawMaterialService.delete(id);
    }
}
