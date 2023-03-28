package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class EmailException extends CustomException {

    private EmailExceptionType emailExceptionType;

    public EmailException(EmailExceptionType emailExceptionType){
        this.emailExceptionType = emailExceptionType;
    }
    @Override
    public CustomExceptionType getExceptionType() {
        return this.emailExceptionType;
    }
}
