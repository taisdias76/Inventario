package com.tais.inventario.exceptions.response;

import com.fasterxml.jackson.annotation.JsonFormat;

public record InventoryError(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        String timestamp,
        String message,
        Integer code,
        String details
) {
}
