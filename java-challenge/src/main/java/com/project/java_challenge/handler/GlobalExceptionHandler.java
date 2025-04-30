package com.project.java_challenge.handler;

import com.project.java_challenge.exceptions.InvalidAccreditationRequestBodyException;
import com.project.java_challenge.exceptions.MinimumCostPathException;
import com.project.java_challenge.exceptions.PointOfSaleCostNotFoundException;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            if (fieldName.contains(".")) {
                fieldName = fieldName.substring(fieldName.lastIndexOf(".") + 1);
            }
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PointOfSaleCostNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePointOfSaleCostNotFound(PointOfSaleCostNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
    
    @ExceptionHandler(PointOfSaleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePointOfSaleNotFound(PointOfSaleNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "The Request Body cannot be empty or null.");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Required parameter is missing: " + ex.getParameterName());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MinimumCostPathException.class)
    public ResponseEntity<Map<String, String>> handleMinimumCostPathException(MinimumCostPathException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(InvalidAccreditationRequestBodyException.class)
    public ResponseEntity<Map<String, String>> handleInvalidAccreditationRequestBodyException(InvalidAccreditationRequestBodyException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}