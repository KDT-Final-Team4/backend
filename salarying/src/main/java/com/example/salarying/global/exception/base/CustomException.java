package com.example.salarying.global.exception.base;

public abstract class CustomException extends RuntimeException{
    public abstract CustomExceptionType getExceptionType();
}
