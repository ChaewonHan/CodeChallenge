package com.project.challenge.exception;

public class LoginFailException extends RuntimeException {

    public LoginFailException(String message) {
        super(message);
    }
}
