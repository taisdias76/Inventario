package com.tais.inventario.service;

import com.tais.inventario.entities.Supplier;
import com.tais.inventario.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository repository;

    @Autowired
    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    public Supplier create(Supplier request) {
        return repository.save(request);
    }

    public List<Supplier> getAll() {
        return repository.findAll();
    }
}
