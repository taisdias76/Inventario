package com.tais.inventario.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        CategoryDTO category,
        String description,
        BigDecimal price,
        SupplierDTO supplier,
        BigDecimal quantity
) {}
