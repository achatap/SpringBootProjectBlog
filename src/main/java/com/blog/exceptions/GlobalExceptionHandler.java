package com.blog.exceptions;

import com.blog.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFound ex){
        String message = ex.getMessage();
        APIResponse apiResponse= new APIResponse(message, false, LocalDateTime.now());
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }
}
