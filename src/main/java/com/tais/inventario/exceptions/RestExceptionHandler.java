package com.tais.inventario.exceptions;

import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.exceptions.response.ApiError;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InventoryNotFoundException.class)
  public ResponseEntity<ApiError> handleInventoryNotFoundException(InventoryNotFoundException ex) {
    ApiError error = buildApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    ApiError error = buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  private ApiError buildApiError(HttpStatus status, String message) {
    return new ApiError(LocalDateTime.now().toString(), status.name(), status.value(), message);
  }
}
