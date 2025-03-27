package com.tais.inventario.service;

import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.entities.Product;
import com.tais.inventario.mappers.ProductMapper;
import com.tais.inventario.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDTO create(ProductDTO request) {
        Product mapped = mapper.toEntity(request);
        Product product = repository.save(mapped);
        return mapper.toDTO(product);
    }

    public List<ProductDTO> getAll() {
        List<Product> list = repository.findAll();
        return mapper.toDTOList(list);
    }
}
