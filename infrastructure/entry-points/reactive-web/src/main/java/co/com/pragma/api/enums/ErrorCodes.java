package co.com.pragma.api.enums;

import co.com.pragma.api.exceptions.JakartaValidationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
    INVALID_FIELD("400_INVALID_FIELD", HttpStatus.BAD_REQUEST, JakartaValidationException.class),
    INTERNAL_SERVER_ERROR("500_INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, RuntimeException.class);

    private final String code;
    private final HttpStatus status;
    private final Class<? extends RuntimeException> exceptionClass;

    ErrorCodes(String code, HttpStatus status, Class<? extends RuntimeException> exceptionClass) {
        this.code = code;
        this.status = status;
        this.exceptionClass = exceptionClass;
    }
}