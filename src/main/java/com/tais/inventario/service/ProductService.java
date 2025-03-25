package com.tais.inventario.service;

import com.tais.inventario.entities.Product;
import com.tais.inventario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product request) {
        return repository.save(request);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}
