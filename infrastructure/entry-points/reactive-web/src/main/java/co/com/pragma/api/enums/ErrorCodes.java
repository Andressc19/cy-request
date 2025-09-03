package co.com.pragma.api.enums;

import co.com.pragma.api.exceptions.JakartaValidationException;
import co.com.pragma.model.loan.exceptions.LoanAmountOutRangeException;
import co.com.pragma.model.user.exceptions.UserDoesntExistsException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
    INVALID_FIELD("400_INVALID_FIELD", HttpStatus.BAD_REQUEST, JakartaValidationException.class),
    USER_NOT_FOUND("404_USER_NOT_FOUND", HttpStatus.NOT_FOUND, UserDoesntExistsException.class),
    LOAN_AMOUNT_OUT_RANGE("400_AMOUNT_OUT_RANGE", HttpStatus.BAD_REQUEST, LoanAmountOutRangeException.class),
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