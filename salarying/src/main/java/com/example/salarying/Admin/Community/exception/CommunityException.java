package com.example.salarying.Admin.Community.exception;

import com.example.salarying.global.exception.base.CustomException;
import com.example.salarying.global.exception.base.CustomExceptionType;

public class CommunityException extends CustomException {

    private final CommunityExceptionType communityExceptionType;

    public CommunityException (CommunityExceptionType communityExceptionType) {
        this.communityExceptionType = communityExceptionType;

    }

    @Override
    public CustomExceptionType getExceptionType() {
        return this.communityExceptionType;
    }
}
