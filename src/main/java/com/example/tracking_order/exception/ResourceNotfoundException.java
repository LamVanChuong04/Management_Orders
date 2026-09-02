package com.example.tracking_order.exception;

public class ResourceNotfoundException extends RuntimeException {
    public ResourceNotfoundException() {
        super("Resource not found");
    }
}
