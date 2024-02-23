package it.epicode.PrenotazioneEvento.security;

import org.springframework.http.HttpStatus;

public class CustomJwtAuthenticationException extends RuntimeException {

    private HttpStatus httpStatus;

    public CustomJwtAuthenticationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}