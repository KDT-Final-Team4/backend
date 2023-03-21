package com.example.salarying.global.exception;

import com.example.salarying.Corporation.User.exception.UserException;
import com.example.salarying.global.dto.ErrorDTO;
import com.example.salarying.global.exception.base.CustomException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CustomExceptionHandler implements ErrorController {

    //custom exception 처리 핸들러
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity handleUserException(CustomException ce){
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error ,ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity handleGlobalException(GlobalException ce){
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error ,ce.getExceptionType().getHttpStatus());
    }




}
