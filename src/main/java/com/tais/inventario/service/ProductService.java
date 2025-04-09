package com.tais.inventario.service;

import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.dtos.ProductRequest;
import com.tais.inventario.entities.Category;
import com.tais.inventario.entities.Product;
import com.tais.inventario.entities.Supplier;
import com.tais.inventario.mappers.CategoryMapper;
import com.tais.inventario.mappers.ProductMapper;
import com.tais.inventario.mappers.SupplierMapper;
import com.tais.inventario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private final ProductRepository repository;
  private final ProductMapper mapper;
  private final SupplierService supplierService;
  private final CategoryService categoryService;

  @Autowired
  public ProductService(ProductRepository repository, ProductMapper mapper, SupplierService supplierService, CategoryService categoryService) {
    this.repository = repository;
    this.mapper = mapper;
    this.supplierService = supplierService;
    this.categoryService = categoryService;
  }

  public ProductDTO create(ProductRequest request) {
    Category category = categoryService.findById(request.categoryId());
    Supplier supplier = supplierService.findById(request.supplierId());
    // Map the request to the entity
    Product mapped = mapper.fromRequest(request, category, supplier);

    Product product = repository.save(mapped);
    return mapper.toDTO(product);
  }

  public List<ProductDTO> getAll() {
    List<Product> list = repository.findAll();
    return mapper.toDTOList(list);
  }
}
