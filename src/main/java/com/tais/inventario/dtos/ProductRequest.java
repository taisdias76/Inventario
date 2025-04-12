package com.tais.inventario.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Nome é obrigatório.")
        String name,

        @NotBlank(message = "Descrição é obrigatória.")
        String description,

        @NotNull(message = "Preço é obrigatório.")
        @PositiveOrZero(message = "O preço deve ser positivo ou zero.")
        BigDecimal price,

        @NotNull(message = "Quantidade é obrigatória.")
        @PositiveOrZero(message = "A quantidade deve ser positiva ou zero.")
        BigDecimal quantity,

        @NotNull(message = "O ID da categoria é obrigatório.")
        Long categoryId,

        @NotNull(message = "O ID do fornecedor é obrigatório.")
        Long supplierId
) {
}
