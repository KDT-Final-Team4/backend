package com.example.salarying.Admin.Community.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum CommunityExceptionType implements CustomExceptionType {

    NOT_EXIST_TITLE(-301, HttpStatus.BAD_REQUEST, "제목이 입력되지 않았습니다."),
    NOT_EXIST_CONTENT(-302, HttpStatus.BAD_REQUEST, "내용이 입력되지 않았습니다."),
    NOT_EXIST(-303, HttpStatus.BAD_REQUEST, "해당글이 존재하지 않습니다."),
    NOT_EXIST_QUESTION(-304, HttpStatus.BAD_REQUEST, "질문이 입력되지 않았습니다."),
    NOT_EXIST_ANSWER(-305, HttpStatus.BAD_REQUEST, "답변이 입력되지 않았습니다."),
    NOT_EXIST_CATEGORY(-306, HttpStatus.BAD_REQUEST, "카테고리가 입력되지 않았습니다.");

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String errorMsg;

    CommunityExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg) {
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
        return httpStatus;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
