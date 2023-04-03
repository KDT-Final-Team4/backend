package com.example.salarying.Corporation.User.exception;

import com.example.salarying.global.exception.base.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum UserExceptionType implements CustomExceptionType {
    PARSING_FAIL(-101,HttpStatus.BAD_REQUEST,"토큰 파싱에 실패했습니다."),
    ACCESS_TOKEN_UN_AUTHORIZED(-102,HttpStatus.BAD_REQUEST,"Access Token이 없거나 유효하지 않습니다. '/refresh' 으로 재요청 하십시오."),
    ALREADY_EXIST(-103, HttpStatus.BAD_REQUEST, "이미 가입된 Email값 입니다."),
    NOT_EMAIL_FORMAT(-104, HttpStatus.BAD_REQUEST, "올바른 이메일 형식이 아닙니다."),
    NOT_PASSWORD_FORMAT(-105, HttpStatus.BAD_REQUEST, "올바른 비밀번호 형식이 아닙니다."),
    NOT_NUMBER_FORMAT(-106, HttpStatus.BAD_REQUEST, "올바른 전화번호 형식이 아닙니다."),
    NOT_EXIST_NAME(-107, HttpStatus.BAD_REQUEST, "회사명이 입력되지 않았습니다."),
    NOT_EXIST_ACCOUNT(-107, HttpStatus.BAD_REQUEST, "존재하지 않는 계정입니다."),
    UNMATCHED_PASSWORD(-107, HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    NOT_LOGGED_IN (-108,HttpStatus.BAD_REQUEST, "로그인이 되어있지 않습니다."),
    ALREADY_USED (-109,HttpStatus.BAD_REQUEST, "이미 사용중인 비밀번호 입니다."),
    EMPTY_PASSWORD (-110,HttpStatus.BAD_REQUEST, "입력된 비밀번호가 없습니다."),
    EMPTY_UPDATE (-111,HttpStatus.BAD_REQUEST, "빈 값이 들어올 수 없습니다.")
    ;

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMsg;

    UserExceptionType(int errorCode, HttpStatus httpStatus, String errorMsg){
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
