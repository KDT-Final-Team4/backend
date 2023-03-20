package com.example.salarying.global.exception.base;

import org.springframework.http.HttpStatus;

public interface CustomExceptionType {

    String getMessage();
    HttpStatus getHttpStatus();
    int getErrorCode();
}
