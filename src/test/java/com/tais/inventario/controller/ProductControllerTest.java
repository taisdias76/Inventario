package com.tais.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.dtos.ProductDTO;
import com.tais.inventario.dtos.ProductRequest;
import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProductService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        ProductController controller = new ProductController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void create() throws Exception {
        ProductRequest request = new ProductRequest("Produto A", "Produto A", new BigDecimal("50.00"), new BigDecimal("1"), 1L, 1L);
        ProductDTO response = new ProductDTO(1L, "Produto A", new CategoryDTO(1L, "Eletronicos"),
                "Produto A", new BigDecimal("50.00"), new SupplierDTO(1L, "Fornecedor A"), new BigDecimal("1"));

        when(service.create(any(ProductRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Produto A"))
            .andExpect(jsonPath("$.category.id").value(1))
            .andExpect(jsonPath("$.category.category").value("Eletronicos"))
            .andExpect(jsonPath("$.description").value("Produto A"))
            .andExpect(jsonPath("$.price").value(50.00))
            .andExpect(jsonPath("$.supplier.id").value(1))
            .andExpect(jsonPath("$.supplier.name").value("Fornecedor A"));

        verify(service).create(any(ProductRequest.class));
    }

    @Test
    void getAll() throws Exception {
        List<ProductDTO> list = List.of(new ProductDTO(1L, "Produto A", new CategoryDTO(1L, "Eletronicos"),
                "Produto A", new BigDecimal("50.00"), new SupplierDTO(1L, "Fornecedor A"), new BigDecimal("1")),
                new ProductDTO(2L, "Produto B", new CategoryDTO(1L, "Eletronicos"),
                "Produto B", new BigDecimal("50.00"), new SupplierDTO(1L, "Fornecedor A"), new BigDecimal("1")));
        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Produto A"))
                .andExpect(jsonPath("$[0].category.id").value(1))
                .andExpect(jsonPath("$[0].category.category").value("Eletronicos"))
                .andExpect(jsonPath("$[0].description").value("Produto A"));

        verify(service).getAll();
    }
}