package com.tais.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tais.inventario.dtos.CategoryDTO;
import com.tais.inventario.dtos.SupplierDTO;
import com.tais.inventario.service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class SupplierControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SupplierService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        SupplierController controller = new SupplierController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void create() throws Exception {
        SupplierDTO request = new SupplierDTO(null, "Fornecedor A");
        SupplierDTO response = new SupplierDTO(1L, "Fornecedor A");

        when(service.create(any(SupplierDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fornecedor A"));

        verify(service).create(any(SupplierDTO.class));
    }

    @Test
    void getAll() throws Exception {
        List<SupplierDTO> list = List.of(new SupplierDTO(1L, "Fornecedor A"), new SupplierDTO(2L, "Fornecedor B"));
        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Fornecedor A"));

        verify(service).getAll();
    }
}