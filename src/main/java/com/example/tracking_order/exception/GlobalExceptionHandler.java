package com.example.tracking_order.exception;

import com.example.tracking_order.dto.response.BaseResponse;
import com.example.tracking_order.dto.response.FieldViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldViolation> violations = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            violations.add(new FieldViolation(fieldName, errorMessage));
        }

        BaseResponse<Object> response = new BaseResponse<>();
        response.getMeta().setCode(HttpStatus.BAD_REQUEST.value());
        response.getMeta().setErrors(violations);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotfoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleResourceNotfoundException(ResourceNotfoundException ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.getMeta().setCode(HttpStatus.NOT_FOUND.value());
        response.getMeta().setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.getMeta().setCode(HttpStatus.BAD_REQUEST.value());
        response.getMeta().setMessage("Path variable validation failed");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
