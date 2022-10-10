package com.digital.commonlibrary.exception.handler;

import com.digital.commonlibrary.enums.ErrorCode;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.commonlibrary.exception.ApiException;
import com.digital.commonlibrary.exception.RequestInvalidException;
import com.digital.commonlibrary.exception.ResourceNotFoundException;
import com.digital.commonlibrary.exception.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Order
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Handling message not readable", ex);
        String message = "Malformed JSON request";
        String description = "JSON request cannot be deserialized";
        return buildResponseEntity(
                new ApiException(
                        HttpStatus.BAD_REQUEST, ErrorCode.MALFORMED_JSON, message, description));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Handling method argument not valid", ex);
        ApiException apiError =
                new ApiException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                ex.getBindingResult().getAllErrors().stream()
                        .map(e -> new ValidationError(e))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RequestInvalidException.class)
    public ResponseEntity<Object> handleRequestInvalidException(
            RequestInvalidException requestInvalidException) {
        log.error("Handling method argument not valid", requestInvalidException);
        ApiException apiError =
                new ApiException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                requestInvalidException.getValidationError().stream()
                        .map(e -> new ValidationError(e))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
        log.error("Handling method argument not valid", ex);
        ApiException apiError =
                new ApiException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCode.VALIDATION_ERROR,
                        "Validation Error",
                        "There are some data elements missing or invalid");
        apiError.setValidationErrors(
                ex.getConstraintViolations().stream()
                        .map(
                                e ->
                                        new ValidationError(
                                                new ObjectError(
                                                        e.getPropertyPath()
                                                                .toString()
                                                                .substring(e.getPropertyPath().toString().indexOf(".") + 1),
                                                        e.getMessage())))
                        .collect(Collectors.toList()));
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        log.error("Handling resource not found exception", ex);

        ApiException apiError =
                new ApiException(
                        HttpStatus.NOT_FOUND,
                        ex.getErrorCode() != null ? ex.getErrorCode() : ErrorCode.RESOURCE_NOT_FOUND,
                        "Resource not found",
                        ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleAllExceptions(CustomException ex) {
        log.error("Handling general exception", ex);
        ApiException apiError =
                new ApiException(
                        ex.getHttpStatus(),
                        ex.getErrorCode(),
                        ex.getMessage(), "");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.error("Handling general exception", ex);
        ApiException apiError =
                new ApiException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCode.INTERNAL_SERVER_ERROR,
                        "Internal Server Error",
                        ex.getMessage());
        return buildResponseEntity(apiError);
    }

    //    @ExceptionHandler(AccessDeniedException.class)
//    public final ResponseEntity<Object> handleAccessDeniedExceptions(AccessDeniedException ex) {
//        log.error("Handling access denied exception", ex);
//        DigitalApiException apiError =
//                new DigitalApiException(
//                        HttpStatus.FORBIDDEN, ErrorCode.ACCESS_DENIED, "Access is denied", ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

//    @ExceptionHandler(MissingRequestHeaderException.class)
//    public final ResponseEntity<Object> handerMissingRequestHeaderException(MissingRequestHeaderException exception) {
//
//        DigitalApiException digitalApiException = new DigitalApiException(
//                HttpStatus.FORBIDDEN, ErrorCode.ACCESS_DENIED, "Access is denied", exception.getMessage()
//        );
//        return buildResponseEntity(digitalApiException);
//    }

    private ResponseEntity<Object> buildResponseEntity(ApiException apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }
}
