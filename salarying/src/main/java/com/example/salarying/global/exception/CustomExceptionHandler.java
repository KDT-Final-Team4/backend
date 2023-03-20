package com.example.salarying.global.exception;

import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.global.dto.ErrorDTO;
import com.example.salarying.global.exception.base.CustomException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {

    //custom exception 처리 핸들러
    @ExceptionHandler(value = UserException.class)
    public ErrorDTO handleUserException(CustomException ce){
        return ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();
    }

    @ExceptionHandler(value = GlobalException.class)
    public ErrorDTO handleGlobalException(CustomException ce){
        return ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())

                .errorMessage(ce.getExceptionType().getMessage())
                .build();
    }



}
