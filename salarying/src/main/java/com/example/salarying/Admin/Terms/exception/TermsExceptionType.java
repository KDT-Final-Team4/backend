package com.example.salarying.Admin.Terms.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum TermsExceptionType implements CustomExceptionType {
    ALREADY_EXIST(-201,HttpStatus.BAD_REQUEST,"해당 버전의 약관이 이미 존재합니다"),
    INVALID_TYPE(-202,HttpStatus.BAD_REQUEST,"존재하지 않는 약관 타입입니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMsg;

    TermsExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg){
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return this.errorMsg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
}
