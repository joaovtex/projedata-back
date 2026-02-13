package com.challenge.projedata.dtos;

public class ProductRawMaterialRequestDTO {
    private Integer productId;
    private Integer rawMaterialId;
    private Integer requiredQuantity;

    public ProductRawMaterialRequestDTO() {}

    public Integer getProductId() {
        return productId;
    }

    public Integer getRawMaterialId() {
        return rawMaterialId;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setRawMaterialId(Integer rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}
