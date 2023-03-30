package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum RecruitingExceptionType implements CustomExceptionType {

    NOT_EXIST_TITLE(-201, HttpStatus.BAD_REQUEST, "공고제목이 입력되지 않았습니다."),
    NOT_EXIST_TASK(-202, HttpStatus.BAD_REQUEST, "직무가 입력되지 않았습니다."),
    NOT_EXIST_PROGRESS(-203, HttpStatus.BAD_REQUEST, "채용전형이 입력되지 않았습니다."),

    NOT_EXIST(-204, HttpStatus.BAD_REQUEST, "해당 채용공고가 없습니다."),

    NOT_EXIST_ID(-205, HttpStatus.BAD_REQUEST, "채용공고를 입력해주세요."),

    NOT_STATUS_FORMAT(-206, HttpStatus.BAD_REQUEST, "올바른 전형을 입력해주세요.")



    ;

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMsg;

    RecruitingExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg){
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
