package com.shop.controller;

import com.shop.exception.OrderAlreadyPaidException;
import com.shop.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseBody
    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<?> handleException(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = OrderAlreadyPaidException.class)
    public ResponseEntity<?> handleException(OrderAlreadyPaidException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
