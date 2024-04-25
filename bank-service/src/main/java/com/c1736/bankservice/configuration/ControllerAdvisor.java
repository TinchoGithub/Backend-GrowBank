package com.c1736.bankservice.configuration;

import com.c1736.bankservice.service.exceptions.AccountBankNotFound;
import com.c1736.bankservice.service.exceptions.NoDataFound;
import com.c1736.bankservice.service.exceptions.UnauthorizedException;
import com.c1736.bankservice.service.exceptions.UserNotFound;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.c1736.bankservice.configuration.Constants.ACCOUNT_BANK_NOT_FOUND_MESSAGE;
import static com.c1736.bankservice.configuration.Constants.NO_DATA_FOUND_MESSAGE;
import static com.c1736.bankservice.configuration.Constants.RESPONSE_MESSAGE_KEY;
import static com.c1736.bankservice.configuration.Constants.UNAUTHORIZED_MESSAGE;
import static com.c1736.bankservice.configuration.Constants.USER_NOT_FOUND_MESSAGE;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleBindExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AccountBankNotFound.class)
    public ResponseEntity<Map<String, String>> handleAccountBankNotFound(
            AccountBankNotFound accountBankNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, ACCOUNT_BANK_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(
            UserNotFound userNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorized(
            UnauthorizedException unauthorizedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, UNAUTHORIZED_MESSAGE));
    }

    @ExceptionHandler(NoDataFound.class)
    public ResponseEntity<Map<String, String>> handleNoDataFound(
            NoDataFound noDataFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }
}
