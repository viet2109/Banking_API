package com.example.demo.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private Error error;
    public AppException(Error error) {
        super(error.getMessage());
        this.error = error;
    }
}
