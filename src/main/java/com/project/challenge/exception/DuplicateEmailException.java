package com.project.challenge.exception;

public class DuplicateEmailException extends IllegalStateException {

    public DuplicateEmailException(String s) {
        super(s);
    }
}
