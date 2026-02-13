package com.challenge.projedata.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_raw_material")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rm_code")
    private Integer id;

    @Column(name = "rm_name", nullable = false)
    private String name;

    @Column(name = "rm_quantity", nullable = false)
    private Integer stockQuantity;

    public RawMaterial(){}

    public RawMaterial(Integer id, String name, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
