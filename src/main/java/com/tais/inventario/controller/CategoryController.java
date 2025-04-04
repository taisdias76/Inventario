package com.tais.inventario.controller;

import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO request) {
        CategoryDTO dto = service.create(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/categorys")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categoryDTOS = service.getAll();
        return ResponseEntity.ok(categoryDTOS);
    }
}
