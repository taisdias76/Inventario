package com.tais.inventario.service;

import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.entities.Supplier;
import com.tais.inventario.mappers.SupplierMapper;
import com.tais.inventario.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {
    @InjectMocks
    private SupplierService service;

    @Mock
    private SupplierRepository repository;

    @Mock
    private SupplierMapper mapper;

    @Test
    void create() {
        SupplierDTO request = new SupplierDTO(null, "Fornecedor Nome");
        Supplier supplier = new Supplier();
        supplier.setName("Fornecedor Nome");

        Supplier savedSupplier = new Supplier();
        savedSupplier.setId(1L);
        savedSupplier.setName("Fornecedor Nome");

        SupplierDTO expectedDTO = new SupplierDTO(1L, "Fornecedor Nome");

        when(mapper.toEntity(request)).thenReturn(supplier);
        when(repository.save(supplier)).thenReturn(savedSupplier);
        when(mapper.toDTO(savedSupplier)).thenReturn(expectedDTO);

        SupplierDTO result = service.create(request);

        assertNotNull(result);
        assertEquals(expectedDTO, result);

        verify(mapper, times(1)).toEntity(request);
        verify(repository, times(1)).save(supplier);
        verify(mapper, times(1)).toDTO(savedSupplier);
    }

    @Test
    void getAll() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Fornecedor");

        SupplierDTO dto = new SupplierDTO(1L, "Fornecedor");

        when(repository.findAll()).thenReturn(List.of(supplier));
        when(mapper.toDTOList(any())).thenReturn(List.of(dto));

        List<SupplierDTO> result = service.getAll();

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

        List<SupplierDTO> result = service.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDTOList(any());
    }

    @Test
    void findById_whenFound() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Fornecedor");

        when(repository.findById(1L)).thenReturn(java.util.Optional.of(supplier));

        Supplier result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Fornecedor", result.getName());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findById_whenNotFound() {
        when(repository.findById(12L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.findById(12L);
        });

        assertEquals("Supplier not found", exception.getMessage());

        verify(repository, times(1)).findById(12L);
    }
}