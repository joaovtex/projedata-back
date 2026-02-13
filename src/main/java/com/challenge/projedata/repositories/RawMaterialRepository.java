package com.challenge.projedata.repositories;

import com.challenge.projedata.entities.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Integer> {
}
