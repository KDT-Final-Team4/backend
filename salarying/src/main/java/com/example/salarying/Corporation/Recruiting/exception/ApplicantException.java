package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class ApplicantException extends CustomException {

    private ApplicantExceptionType applicantExceptionType;

    public ApplicantException(ApplicantExceptionType applicantExceptionType){
        this.applicantExceptionType = applicantExceptionType;
    }
    @Override
    public CustomExceptionType getExceptionType() {
        return this.applicantExceptionType;
    }
}
