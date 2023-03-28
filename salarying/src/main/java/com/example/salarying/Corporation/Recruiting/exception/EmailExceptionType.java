package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum EmailExceptionType implements CustomExceptionType {

    NOT_EXIST_SUBJECT(-401, HttpStatus.BAD_REQUEST, "메일제목이 없습니다"),
    NOT_EXIST_CONTENT(-402, HttpStatus.BAD_REQUEST, "메일내용이 없습니다"),
    NOT_EXIST_EMAIL(-403, HttpStatus.BAD_REQUEST, "이메일이 없습니다."),

    NOT_EMAIL_FORMAT(-404, HttpStatus.BAD_REQUEST, "올바른 이메일형식이 아닙니다."),

    NOT_EXIST_APPLICANT(-404, HttpStatus.BAD_REQUEST, "해당 지원자가 없습니다.")



            ;

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMsg;

    EmailExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg){
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMsg = errorMsg;
    }
    @Override
    public String getMessage() {
        return this.errorMsg;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
}
