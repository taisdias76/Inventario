package com.tais.inventario.controller;

import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.dtos.ProductRequest;
import com.tais.inventario.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
  private final ProductService service;

  @Autowired
  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping("/product")
  public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductRequest request) {
    ProductDTO dto = service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductDTO>> getAll() {
    List<ProductDTO> productDTOS = service.getAll();
    return ResponseEntity.ok(productDTOS);
  }
}
