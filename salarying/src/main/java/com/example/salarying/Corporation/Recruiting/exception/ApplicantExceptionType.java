package com.example.salarying.Corporation.Recruiting.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum ApplicantExceptionType implements CustomExceptionType {

    ALREADY_EXIST_APPLICANT(-501, HttpStatus.BAD_REQUEST, "이미 존재하는 지원자입니다."),
    NOT_EXIST_RECRUITING(-502, HttpStatus.BAD_REQUEST, "채용공고가 선택되지 않았습니다."),
    NOT_EXIST_EMAIL(-503, HttpStatus.BAD_REQUEST, "이메일을 입력해주세요."),
    NOT_EXIST_NAME(-504, HttpStatus.BAD_REQUEST, "이름을 입력해주세요."),
    NOT_EXIST_PHONE(-505, HttpStatus.BAD_REQUEST, "전화번호를 입력해주세요."),
    NOT_PHONE_FORMAT(-506, HttpStatus.BAD_REQUEST, "올바른 전화번호 형식이 아닙니다."),
    NOT_EMAIL_FORMAT(-507, HttpStatus.BAD_REQUEST, "올바른 이메일 형식이 아닙니다.")



            ;

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMsg;

    ApplicantExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg){
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
