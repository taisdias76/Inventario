package com.tais.inventario.dtos;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequestDTO (
    @NotBlank(message = "Nome é obrigatório.")
    String name,

    @NotNull(message = "ID da categoria é obrigatório.")
    Long categoryId,

    @NotBlank(message = "Descrição é obrigatória.")
    String description,

    @NotNull(message = "Preço é obrigatório.")
    @PositiveOrZero(message = "Preço deve ser positivo.")
    BigDecimal price,

    @NotNull(message = "ID do fornecedor é obrigatório.")
    Long supplierId,

    @NotNull(message = "Quantidade é obrigatória.")
    @PositiveOrZero(message = "Quantidade deve ser positiva.")
    BigDecimal quantity
) {}