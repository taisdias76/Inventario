package com.tais.inventario.dtos;

import jakarta.validation.constraints.NotBlank;

public record SupplierRequestDTO(
        @NotBlank(message = "Nome do fornecedor é obrigatório.")
        String name
) {
}
