package com.challenge.projedata.services;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.repositories.ProductRawMaterialRepository;
import com.challenge.projedata.repositories.ProductRepository;
import com.challenge.projedata.repositories.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRawMaterialService {
    ProductRawMaterialRepository productRawMaterialRepository;
    ProductRepository productRepository;
    RawMaterialRepository rawMaterialRepository;

    public ProductRawMaterialService(
            ProductRawMaterialRepository productRawMaterialRepository,
            ProductRepository productRepository,
            RawMaterialRepository rawMaterialRepository
    ) {
        this.productRawMaterialRepository = productRawMaterialRepository;
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public List<ProductRawMaterial> findAll() {
        return productRawMaterialRepository.findAll();
    }

    public ProductRawMaterial save (Integer productId, Integer rawMaterialId, Integer requiredQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId)
                .orElseThrow(() -> new RuntimeException("Matéria Prima não encontrada."));

        ProductRawMaterial entity = new ProductRawMaterial();
        entity.setProduct(product);
        entity.setRawMaterial(rawMaterial);
        entity.setRequiredQuantity(requiredQuantity);

        return productRawMaterialRepository.save(entity);
    }

    public ProductRawMaterial update(Integer id, Integer requiredQuantity) {
        ProductRawMaterial entity = productRawMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Associação não encontrada."));

        entity.setRequiredQuantity(requiredQuantity);

        return productRawMaterialRepository.save(entity);
    }

    public void delete(Integer id) {
        productRawMaterialRepository.deleteById(id);
    }

}
