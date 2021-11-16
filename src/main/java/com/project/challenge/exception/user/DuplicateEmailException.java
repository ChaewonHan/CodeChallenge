package com.project.challenge.exception.user;

public class DuplicateEmailException extends IllegalStateException {

    public DuplicateEmailException(String s) {
        super(s);
    }
}
