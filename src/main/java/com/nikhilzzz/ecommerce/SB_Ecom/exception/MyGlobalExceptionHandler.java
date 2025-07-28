package com.nikhilzzz.ecommerce.SB_Ecom.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MyGlobalExceptionHandler {


    // Handling Method Not Found Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err)->{
            String fieldName =((FieldError)err).getField(); // Get the field name out of the error that had bad request
            String errorMsg = err.getDefaultMessage(); // Getting the default message
            response.put(fieldName,errorMsg); // adding it to response
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }


}
