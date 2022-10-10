package com.digital.commonlibrary.exception;

import com.digital.commonlibrary.enums.ErrorCode;
import lombok.Getter;

import static java.lang.String.format;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public ResourceNotFoundException(final Class resourceClazz, final String resourceId) {
        super(format("Resource %s not found for %s", resourceClazz.getSimpleName(), resourceId));
    }

    public ResourceNotFoundException(final Class resourceClazz, final String resourceId, ErrorCode errorCode) {
        super(format("Resource %s not found for %s", resourceClazz.getSimpleName(), resourceId));
        this.errorCode = errorCode;
    }
}
