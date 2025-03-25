package com.tais.inventario.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank(message = "Nome da categoria é obrigatório.") String category) {
}
