package com.challenge.projedata.dtos;

import java.math.BigDecimal;

public class ProductionResponseDTO {
    private Integer productId;
    private String productName;
    private Integer possibleQuantity;
    private BigDecimal unitValue;
    private BigDecimal totalValue;

    public ProductionResponseDTO(
            Integer productId,
            String productName,
            Integer quantity,
            BigDecimal unitValue,
            BigDecimal totalValue
    ) {
        this.productId = productId;
        this.productName = productName;
        this.possibleQuantity = quantity;
        this.unitValue = unitValue;
        this.totalValue = totalValue;
    }


    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPossibleQuantity() {
        return possibleQuantity;
    }

    public BigDecimal getUnitValue() {
        return unitValue;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPossibleQuantity(Integer possibleQuantity) {
        this.possibleQuantity = possibleQuantity;
    }

    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
