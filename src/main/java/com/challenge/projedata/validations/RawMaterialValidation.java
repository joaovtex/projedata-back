package com.challenge.projedata.validations;

import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.exceptions.BadRequestException;

public class RawMaterialValidation {
    public static void validate(RawMaterial rawMaterial) {
        if (rawMaterial.getName() == null) {
            throw new BadRequestException("Raw Material name must not be null.");
        }

        if (rawMaterial.getName().isEmpty()) {
            throw new BadRequestException("Raw Material name is required.");
        }

        if (rawMaterial.getStockQuantity() == null) {
            throw new BadRequestException("Stock quantity must not be null.");
        }

        if (rawMaterial.getStockQuantity() < 0) {
            throw new BadRequestException("Stock quantity must not be negative.");
        }
    }
}
