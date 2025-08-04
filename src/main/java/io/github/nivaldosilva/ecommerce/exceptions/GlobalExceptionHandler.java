package io.github.nivaldosilva.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateBarcodeException.class)
    public ResponseEntity<Object> handleDuplicateBarcodeException(DuplicateBarcodeException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Erro de Conflito", ex.getMessage());
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<Object> handleCpfAlreadyExistsException(CpfAlreadyExistsException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Erro de Conflito", ex.getMessage());
    }
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Erro de Conflito", ex.getMessage());
    }

    @ExceptionHandler({ProductNotFoundException.class, CategoryNotFoundException.class, ClientNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Recurso Não Encontrado", ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Erro de Validação", ex.getMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}