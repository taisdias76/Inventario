package com.tais.inventario.service;

import com.tais.inventario.entities.Category;
import com.tais.inventario.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category request) {
        return repository.save(request);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }
}
