package com.tais.inventario.service;

import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.entities.Supplier;
import com.tais.inventario.mappers.SupplierMapper;
import com.tais.inventario.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
  private final SupplierRepository repository;
  private final SupplierMapper mapper;

  @Autowired
  public SupplierService(SupplierRepository repository, SupplierMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public SupplierDTO create(SupplierDTO request) {
    Supplier mapped = mapper.toEntity(request);
    Supplier supplier = repository.save(mapped);
    return mapper.toDTO(supplier);
  }

  public List<SupplierDTO> getAll() {
    List<Supplier> supplierList = repository.findAll();
    return mapper.toDTOList(supplierList);
  }

  public Supplier findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
  }
}
