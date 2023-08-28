package com.hackathon.orderservice.exception;


import com.hackathon.orderservice.model.ApiResponse;
import com.hackathon.orderservice.model.ErrorMessage;
import com.hackathon.orderservice.model.ErrorMethodNotValidResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ErrorMethodNotValidResponse>> handleInvalidException(MethodArgumentNotValidException exception){
        Map<String, String> error=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(errorMap-> error.put(errorMap.getField(),
                errorMap.getDefaultMessage()));
        ErrorMessage errorMessage=new ErrorMessage("1001",error);
        ApiResponse<ErrorMethodNotValidResponse> response=new ApiResponse<>("Error",null,errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidProductException(
            InvalidProductException exception) {

        Map<String, String> error=new HashMap<>();
        error.put("Error", exception.getMessage());
        ErrorMessage errorMessage=new ErrorMessage("1001",error);
        ApiResponse<String> response=new ApiResponse<>("Error",null,errorMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
}
