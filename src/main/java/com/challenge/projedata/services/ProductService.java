package com.challenge.projedata.services;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.exceptions.BadRequestException;
import com.challenge.projedata.exceptions.ResourceNotFoundException;
import com.challenge.projedata.repositories.ProductRepository;
import com.challenge.projedata.validations.ProductValidation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product save(Product product) {
        ProductValidation.validade(product);

        product.setName(product.getName().trim());

        if (productRepository.existsByNameIgnoreCase(product.getName())) {
            throw new BadRequestException(
                    "A product with this name already existis."
            );
        }

        return productRepository.save(product);
    }

    public Product update(Integer id, Product updated) {
        Product entity = findById(id);

        ProductValidation.validade(updated);

        updated.setName(updated.getName().trim());

        if (productRepository.existsByNameIgnoreCaseAndIdNot(updated.getName(), id)) {
            throw new BadRequestException(
                    "A product with this name already existis."
            );
        }

        entity.setName(updated.getName());
        entity.setValue(updated.getValue());

        return productRepository.save(entity);
    }

    public void delete(Integer id) {
        findById(id);
        productRepository.deleteById(id);
    }

}
