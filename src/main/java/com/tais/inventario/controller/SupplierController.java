package com.tais.inventario.controller;

import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {
    private final SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDTO> create(@RequestBody SupplierDTO request) {
        SupplierDTO dto = service.create(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAll() {
        List<SupplierDTO> suppliers = service.getAll();
        return ResponseEntity.ok(suppliers);
    }
}
