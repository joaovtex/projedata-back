package com.challenge.projedata.controllers;

import com.challenge.projedata.dtos.ProductionResponseDTO;
import com.challenge.projedata.services.ProductionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producao")
public class ProductionController {
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public List<ProductionResponseDTO> calculate() {
        return productionService.calculateProduction();
    }
}
