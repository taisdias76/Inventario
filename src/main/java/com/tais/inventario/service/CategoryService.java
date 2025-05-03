package com.tais.inventario.service;

import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.entities.Category;
import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.mappers.CategoryMapper;
import com.tais.inventario.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  private final CategoryRepository repository;
  private final CategoryMapper mapper;

  @Autowired
  public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public List<CategoryDTO> getAll() {
    List<Category> list = repository.findAll();
    return mapper.toDTOList(list);
  }

  public CategoryDTO create(CategoryDTO dto) {
    Category category = mapper.toEntity(dto);
    Category saved = repository.save(category);
    return mapper.toDTO(saved);
  }

  public Category findById(Long id) {
    return repository.findById(id).orElseThrow(() -> new InventoryNotFoundException("Category not found"));
  }
}
