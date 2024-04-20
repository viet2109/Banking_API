package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {
    DUPLICATED_USER("there is duplicated user information", HttpStatus.CONFLICT),
    ALREADY_EXIST_EMAIL("email already exist", HttpStatus.CONFLICT),
    ALREADY_EXIST_PHONE("phone already exist", HttpStatus.CONFLICT),

    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    ;
    private String message;
    private HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
