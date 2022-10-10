package com.digital.commonlibrary.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class RequestInvalidException extends Exception {

    public List<ObjectError> validationErrors;

    public RequestInvalidException(List<ObjectError> validationErrors) {
        super("Validation errors : " + validationErrors);
        this.validationErrors = validationErrors;
    }

    public List<ObjectError> getValidationError() {
        return validationErrors;
    }
}
