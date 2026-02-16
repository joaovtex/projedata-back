package com.challenge.projedata.controllers;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/id={id}")
    public Product findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PostMapping("/new")
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/edit={id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/delete={id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }
}
