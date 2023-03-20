package com.example.salarying.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;
}
