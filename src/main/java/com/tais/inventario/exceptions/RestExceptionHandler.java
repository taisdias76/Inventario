package com.tais.inventario.exceptions;

import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.exceptions.response.InventoryError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InventoryNotFoundException.class)
  public ResponseEntity<InventoryError> handleInventoryNotFoundException(InventoryNotFoundException ex) {
    InventoryError error = new InventoryError(
            LocalDateTime.now().toString(),
            HttpStatus.NOT_FOUND.name(),
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage()
    );
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request) {
    InventoryError error = new InventoryError(
            LocalDateTime.now().toString(),
            HttpStatus.BAD_REQUEST.name(),
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage()
    );

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}