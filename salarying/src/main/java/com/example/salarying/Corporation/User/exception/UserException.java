package com.example.salarying.Corporation.User.exception;


import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class UserException extends CustomException {

    private UserExceptionType userExceptionType;

    public UserException(UserExceptionType userExceptionType){
        this.userExceptionType = userExceptionType;
    }

    @Override
    public CustomExceptionType getExceptionType() {
        return this.userExceptionType;
    }
}
