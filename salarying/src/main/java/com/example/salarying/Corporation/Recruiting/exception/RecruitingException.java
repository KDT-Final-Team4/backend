package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class RecruitingException extends CustomException {

    private RecruitingExceptionType recruitingExceptionType;

    public RecruitingException(RecruitingExceptionType recruitingExceptionType){
        this.recruitingExceptionType = recruitingExceptionType;
    }
    @Override
    public CustomExceptionType getExceptionType() {
        return this.recruitingExceptionType;
    }
}
