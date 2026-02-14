package com.challenge.projedata.validations;

import com.challenge.projedata.entities.Product;
import com.challenge.projedata.exceptions.BadRequestException;

import java.math.BigDecimal;

public class ProductValidation {
    public static void validade(Product product) {
        System.out.println("passou 2");
        if (product == null) {
            throw new BadRequestException("Product must not be null.");
        }

        if (product.getName().isEmpty()) {
            throw new BadRequestException("Product name is required.");
        }

        if (product.getValue() == null) {
            throw new BadRequestException("Product value is requires.");
        }

        if (product.getValue().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Product value must be greater than zero.");
        }
    }

}
