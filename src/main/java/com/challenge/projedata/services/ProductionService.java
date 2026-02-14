package com.challenge.projedata.services;

import com.challenge.projedata.dtos.ProductionResponseDTO;
import com.challenge.projedata.entities.Product;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.repositories.ProductRawMaterialRepository;
import com.challenge.projedata.repositories.ProductRepository;
import com.challenge.projedata.repositories.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductionService {

    private final ProductRepository productRepository;
    private final ProductRawMaterialRepository productRawMaterialRepository;

    public ProductionService(
            ProductRepository productRepository,
            ProductRawMaterialRepository productRawMaterialRepository
    ){
        this.productRepository = productRepository;
        this.productRawMaterialRepository = productRawMaterialRepository;
    }

    public List<ProductionResponseDTO> calculateProduction() {
        List<Product> products = productRepository.findAll();
        List<ProductionResponseDTO> response = new ArrayList<>();

        for (Product product : products) {
            List<ProductRawMaterial> relations = productRawMaterialRepository.findByProduct_Id(product.getId());

            if (relations.isEmpty()) continue;

            int minPossible = Integer.MAX_VALUE;

            for (ProductRawMaterial relation : relations) {
                int stock = relation.getRawMaterial().getStockQuantity();
                int required = relation.getRequiredQuantity();

                int possible = stock / required;

                if (possible < minPossible) {
                    minPossible = possible;
                }
            }

            if (minPossible > 0) {
                BigDecimal totalValue = product.getValue().multiply(BigDecimal.valueOf(minPossible));

                response.add( new ProductionResponseDTO(
                        product.getId(),
                        product.getName(),
                        minPossible,
                        product.getValue(),
                        totalValue
                        )
                );
            }
        }

        response.sort(Comparator.comparing(ProductionResponseDTO::getUnitValue).reversed());

        return response;
    }
}
