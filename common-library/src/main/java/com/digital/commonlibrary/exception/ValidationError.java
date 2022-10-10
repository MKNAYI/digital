package com.digital.commonlibrary.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@NoArgsConstructor
@Data
@JsonPropertyOrder({"target", "message"})
public class ValidationError {

    private String target;

    private String message;

    public ValidationError(ObjectError error) {

        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            this.target = fieldError.getField();
        } else {
            this.target = error.getObjectName();
        }
        this.message = error.getDefaultMessage();
    }
}
