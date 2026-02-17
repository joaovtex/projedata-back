package com.challenge.projedata.controllers;

import com.challenge.projedata.entities.RawMaterial;
import com.challenge.projedata.services.RawMaterialService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/raw-materials")
public class RawMaterialController {
    private final RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping
    public List<RawMaterial> findAll(Sort sort) {
        return rawMaterialService.findAll(sort);
    }

    @GetMapping("/id={id}")
    public RawMaterial findById(@PathVariable Integer id) {
        return rawMaterialService.findById(id);
    }

    @PostMapping("new")
    public RawMaterial create(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.save(rawMaterial);
    }

    @PutMapping("/edit={id}")
    public RawMaterial update(@PathVariable Integer id, @RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.update(id, rawMaterial);
    }

    @DeleteMapping("/delete={id}")
    public void delete(@PathVariable Integer id) {
        rawMaterialService.delete(id);
    }
}
