package com.example.tracking_order.exception;

public class BusinessException extends  RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
