package com.challenge.projedata.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_p_rm", uniqueConstraints = @UniqueConstraint(columnNames = {"p_code", "rm_code"}))
public class ProductRawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "p_code", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "rm_code", nullable = false)
    private RawMaterial rawMaterial;

    @Column(name = "required_quantity", nullable = false)
    private Integer requiredQuantity;

    public ProductRawMaterial(){}

    public ProductRawMaterial(Integer id, Product product, RawMaterial rawMaterial, Integer requiredQuantity) {
        this.id = id;
        this.product = product;
        this.rawMaterial = rawMaterial;
        this.requiredQuantity = requiredQuantity;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public Integer getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public void setRequiredQuantity(Integer requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }
}
