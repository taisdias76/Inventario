package com.tais.inventario.exceptions;

import com.tais.inventario.exceptions.models.InventoryNotFoundException;
import com.tais.inventario.exceptions.response.InventoryError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestExceptionHandlerTest {
    private final RestExceptionHandler handler = new RestExceptionHandler();

    @Test
    void handleInventoryNotFoundException_shouldReturn404AndBody() {
        InventoryNotFoundException ex = new InventoryNotFoundException("Category not found");

        ResponseEntity<InventoryError> response = handler.handleInventoryNotFoundException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        InventoryError body = response.getBody();
        assertNotNull(body);
        assertEquals(HttpStatus.NOT_FOUND.name(), body.message());
        assertEquals(HttpStatus.NOT_FOUND.value(), body.code());
        assertEquals("Category not found", body.details());
        assertNotNull(body.timestamp());
    }

    @Test
    void handleMethodArgumentNotValid_shouldReturn400AndBody() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getMessage()).thenReturn("validation failed");

        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(ex, headers, HttpStatus.BAD_REQUEST, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        Object maybeBody = response.getBody();
        assertNotNull(maybeBody);
        assertTrue(maybeBody instanceof InventoryError);

        InventoryError body = (InventoryError) maybeBody;
        assertEquals(HttpStatus.BAD_REQUEST.name(), body.message());
        assertEquals(HttpStatus.BAD_REQUEST.value(), body.code());
        assertEquals("validation failed", body.details());
        assertNotNull(body.timestamp());
    }
}