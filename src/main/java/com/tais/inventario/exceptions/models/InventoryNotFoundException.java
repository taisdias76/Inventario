package com.tais.inventario.exceptions.models;

import java.io.Serial;

public class InventoryNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public InventoryNotFoundException(String message) {
    super(message);
  }
}
