package com.example.hotel.exception;

import com.example.hotel.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> handelResourceNotFoundException(ResourceNotFound resourceNotFound){
        ApiResponse apiResponse =  ApiResponse.builder().msg(resourceNotFound.getMessage()).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
