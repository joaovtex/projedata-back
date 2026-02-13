package com.challenge.projedata.services;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.repositories.ProductRepository;
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
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Integer id, Product updated) {
        Product entity = findById(id);

        entity.setName(updated.getName());
        entity.setValue(updated.getValue());

        return productRepository.save(entity);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
