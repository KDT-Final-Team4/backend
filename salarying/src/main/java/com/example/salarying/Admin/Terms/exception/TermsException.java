package com.example.salarying.Admin.Terms.exception;


import com.example.salarying.Corporation.User.exception.UserExceptionType;
import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class TermsException extends CustomException {

    private TermsExceptionType termsExceptionType;

    public TermsException(TermsExceptionType termsExceptionType){
        this.termsExceptionType = termsExceptionType;
    }

    @Override
    public CustomExceptionType getExceptionType() {
        return this.termsExceptionType;
    }
}
