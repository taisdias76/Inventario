package com.tais.inventario.service;

import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.dtos.ProductRequest;
import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.entities.Category;
import com.tais.inventario.entities.Product;
import com.tais.inventario.entities.Supplier;
import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.mappers.ProductMapper;
import com.tais.inventario.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private SupplierService supplierService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService service;

    @Test
    void create() {
        ProductRequest request = new ProductRequest(
                "Notebook",
                "Notebook Gamer",
                new BigDecimal("20.00"),
                new BigDecimal("10"),
                2L,
                3L
        );

        Category category = new Category();
        category.setId(2L);
        category.setCategory("Eletronicos");

        Supplier supplier = new Supplier();
        supplier.setId(3L);
        supplier.setName("Fornecedor A");

        Product mapped = new Product();
        mapped.setName("Notebook");
        mapped.setDescription("Notebook Gamer");
        mapped.setPrice(new BigDecimal("20.00"));
        mapped.setQuantity(new BigDecimal("10"));
        mapped.setCategory(category);
        mapped.setSupplier(supplier);

        Product saved = new Product();
        saved.setName(mapped.getName());
        saved.setDescription(mapped.getDescription());
        saved.setPrice(mapped.getPrice());
        saved.setQuantity(mapped.getQuantity());
        saved.setCategory(category);
        saved.setSupplier(supplier);
        saved.setId(7L);

        ProductDTO dto = new ProductDTO(
                7L,
                "Notebook",
                new CategoryDTO(2L, "Eletronicos"),
                "Notebook Gamer",
                new BigDecimal("20.00"),
                new SupplierDTO(3L, "Fornecedor A"),
                new BigDecimal("10")
        );

        when(categoryService.findById(2L)).thenReturn(category);
        when(supplierService.findById(3L)).thenReturn(supplier);
        when(mapper.fromRequest(any(), any(), any())).thenReturn(mapped);
        when(repository.save(mapped)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(dto);

        ProductDTO result = service.create(request);

        assertNotNull(result);
        assertEquals(7L, result.id());
        assertEquals("Notebook", result.name());
        assertEquals(dto, result);

        verify(categoryService, times(1)).findById(2L);
        verify(supplierService, times(1)).findById(3L);
        verify(mapper, times(1)).fromRequest(any(), any(), any());
        verify(repository, times(1)).save(mapped);
        verify(mapper, times(1)).toDTO(saved);
    }

    @Test
    void create_whenCategoryNotFound() {
        ProductRequest request = new ProductRequest(
                "Arroz",
                "Pacote 5kg",
                new BigDecimal("20.00"),
                new BigDecimal("10"),
                99L,
                3L
        );

        when(categoryService.findById(99L)).thenThrow(new InventoryNotFoundException("Category not found"));

        InventoryNotFoundException ex = assertThrows(InventoryNotFoundException.class, () -> service.create(request));
        assertEquals("Category not found", ex.getMessage());

        verify(categoryService, times(1)).findById(99L);
        verifyNoMoreInteractions(repository, mapper, supplierService);
    }

    @Test
    void getAll() {
        Product product = new Product();
        product.setId(4L);
        product.setName("Notebook");

        ProductDTO dto = new ProductDTO(
                4L,
                "Notebook",
                null,
                null,
                null,
                null,
                null
        );

        when(repository.findAll()).thenReturn(List.of(product));
        when(mapper.toDTOList(any())).thenReturn(List.of(dto));

        List<ProductDTO> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDTOList(any());
    }
}