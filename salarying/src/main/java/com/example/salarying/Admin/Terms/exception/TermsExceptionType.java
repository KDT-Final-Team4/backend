package com.example.salarying.Admin.Terms.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum TermsExceptionType implements CustomExceptionType {
    ALREADY_EXIST(-201,HttpStatus.BAD_REQUEST,"해당 버전의 약관이 이미 존재합니다"),
    INVALID_TYPE(-202,HttpStatus.BAD_REQUEST,"존재하지 않는 약관 타입입니다."),
    NOT_EXIST(-203,HttpStatus.BAD_REQUEST,"존재하지 않는 약관입니다."),
    CHECK_STATUS(-204,HttpStatus.BAD_REQUEST,"이미 설정한 status 값도 동일합니다."),
    CHECK_OTHERS(-205,HttpStatus.BAD_REQUEST,"이미 공개된 약관이 존재합니다."),
    NO_AUTHORITY(-206, HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다"),
    IS_OPENED(-207, HttpStatus.BAD_REQUEST, "공개된 약관은 삭제할 수 없습니다."),
    MUST_OPEN(-208, HttpStatus.BAD_REQUEST, "공개된 약관은 1개 존재해야 합니다.");


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
