package com.csi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<?> employeeNotFound(EmployeeNotFound employeeNotFound) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", employeeNotFound.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException runtimeException) {
        Map<String, Object> map = new HashMap<>();
        map.put("Cause :- ", runtimeException.getCause());
        map.put("Message :- ", runtimeException.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}