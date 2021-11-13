package com.project.challenge.exception;

public class DuplicateUsernameException extends IllegalStateException{

    public DuplicateUsernameException(String s) {
        super(s);
    }
}
