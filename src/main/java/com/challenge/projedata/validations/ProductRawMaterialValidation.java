package com.challenge.projedata.validations;

import com.challenge.projedata.entities.ProductRawMaterial;
import com.challenge.projedata.exceptions.BadRequestException;

public class ProductRawMaterialValidation {
    public static void validateRequiredQuantity(Integer requiredQuantity) {
        if  (requiredQuantity <= 0) {
            throw new BadRequestException("Required raw material quantity must be greater than zero.");
        }
    }
}
