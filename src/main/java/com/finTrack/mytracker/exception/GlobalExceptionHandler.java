package com.finTrack.mytracker.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Enable adding message to error response
 * Spring Boot automatically picks it up and applies it across all your controllers, as long as:
 * It’s annotated with @RestControllerAdvice (or @ControllerAdvice). and It’s in a package that is scanned by Spring Boot
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex, HttpServletRequest request) {

        Map<String, Object> errorBody = createErrorBody(HttpStatus.CONFLICT, ex, request);
        return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        Map<String, Object> errorBody = createErrorBody(HttpStatus.NOT_FOUND, ex, request);
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> createErrorBody(HttpStatus httpStatus, RuntimeException ex, HttpServletRequest request) {
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", OffsetDateTime.now());
        errorBody.put("status", httpStatus.value());
        errorBody.put("error", httpStatus.getReasonPhrase());
        errorBody.put("message", ex.getMessage()); // 👈 custom message
        errorBody.put("path", request.getRequestURI());

        return errorBody;
    }
}
