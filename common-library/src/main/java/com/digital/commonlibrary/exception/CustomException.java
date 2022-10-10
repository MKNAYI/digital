package com.digital.commonlibrary.exception;

import org.springframework.http.HttpStatus;
import com.digital.commonlibrary.enums.ErrorCode;

public class CustomException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
