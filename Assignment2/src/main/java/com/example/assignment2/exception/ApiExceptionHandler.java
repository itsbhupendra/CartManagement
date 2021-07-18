package com.example.assignment2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){

        HttpStatus badRequest=HttpStatus.BAD_REQUEST;
        ApiException z= new ApiException(
                e.getMessage(),
                badRequest
        );

        return new ResponseEntity<>(z,badRequest);
    }
}
