package com.gaurang.loanapproval.exception;

public class InvalidPasswordException
        extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}