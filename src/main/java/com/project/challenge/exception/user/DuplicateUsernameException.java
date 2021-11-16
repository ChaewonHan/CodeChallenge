package com.project.challenge.exception.user;

public class DuplicateUsernameException extends IllegalStateException{

    public DuplicateUsernameException(String s) {
        super(s);
    }
}
