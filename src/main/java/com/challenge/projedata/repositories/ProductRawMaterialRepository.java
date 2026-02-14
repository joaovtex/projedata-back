package com.challenge.projedata.repositories;

import com.challenge.projedata.entities.ProductRawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRawMaterialRepository extends JpaRepository<ProductRawMaterial, Integer> {
    List<ProductRawMaterial> findByProduct_Id(Integer productId);
}
