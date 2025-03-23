package com.tais.inventario.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        CategoryResponseDTO category,
        String description,
        BigDecimal price,
        SupplierResponseDTO supplier,
        BigDecimal quantity
) {}
