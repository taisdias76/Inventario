package com.tais.inventario.service;

import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.entities.Category;
import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.mappers.CategoryMapper;
import com.tais.inventario.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService service;

    @Test
    void getAll() {
        Category category = new Category();
        category.setId(1L);
        category.setCategory("Eletrônicos");

        CategoryDTO dto = new CategoryDTO(1L, "Eletrônicos");

        when(repository.findAll()).thenReturn(List.of(category));
        when(mapper.toDTOList(any())).thenReturn(List.of(dto));

        List<CategoryDTO> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDTOList(any());
    }

    @Test
    void getAll_whenEmpty() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        when(mapper.toDTOList(any())).thenReturn(Collections.emptyList());

        List<CategoryDTO> result = service.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDTOList(any());
    }

    @Test
    void create() {
        CategoryDTO request = new CategoryDTO(null, "Alimentos");

        Category entityToSave = new Category();
        entityToSave.setCategory("Alimentos");

        Category savedEntity = new Category();
        savedEntity.setId(5L);
        savedEntity.setCategory("Alimentos");

        CategoryDTO savedDto = new CategoryDTO(5L, "Alimentos");

        when(mapper.toEntity(request)).thenReturn(entityToSave);
        when(repository.save(entityToSave)).thenReturn(savedEntity);
        when(mapper.toDTO(savedEntity)).thenReturn(savedDto);

        CategoryDTO result = service.create(request);

        assertNotNull(result);
        assertEquals(5L, result.id());
        assertEquals("Alimentos", result.category());

        verify(mapper, times(1)).toEntity(request);
        verify(repository, times(1)).save(entityToSave);
        verify(mapper, times(1)).toDTO(savedEntity);
    }

    @Test
    void findById_whenFound() {
        Category category = new Category();
        category.setId(2L);
        category.setCategory("Bebidas");

        when(repository.findById(2L)).thenReturn(Optional.of(category));

        Category result = service.findById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Bebidas", result.getCategory());

        verify(repository, times(1)).findById(2L);
    }

    @Test
    void findById_whenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class, () -> service.findById(99L));
        assertEquals("Category not found", ex.getMessage());

        verify(repository, times(1)).findById(99L);
    }
}