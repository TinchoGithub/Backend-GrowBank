package com.c1736.messagingservice.configuration.security.exception;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
