package com.example.salarying.global.exception;

import com.example.salarying.Admin.Community.exception.CommunityException;
import com.example.salarying.Admin.Terms.exception.TermsException;
import com.example.salarying.Corporation.Recruiting.exception.ApplicantException;
import com.example.salarying.Corporation.Recruiting.exception.EmailException;
import com.example.salarying.Corporation.Recruiting.exception.RecruitingException;
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
    public ResponseEntity handleUserException(CustomException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error, ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity handleGlobalException(GlobalException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error ,ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = RecruitingException.class)
    public ResponseEntity handleRecruitingException(RecruitingException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error, ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = CommunityException.class)
    public ResponseEntity handleUserException(CommunityException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error ,ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity handleEmailException(EmailException ce){
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error, ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = TermsException.class)
    public ResponseEntity handleTermsException(TermsException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error, ce.getExceptionType().getHttpStatus());
    }

    @ExceptionHandler(value = ApplicantException.class)
    public ResponseEntity handleApplicantException(ApplicantException ce) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode(ce.getExceptionType().getErrorCode())
                .errorMessage(ce.getExceptionType().getMessage())
                .httpStatus(ce.getExceptionType().getHttpStatus())
                .build();

        return new ResponseEntity(error, ce.getExceptionType().getHttpStatus());
    }
}
