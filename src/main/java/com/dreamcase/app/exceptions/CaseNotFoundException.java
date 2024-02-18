package com.dreamcase.app.exceptions;

public class CaseNotFoundException extends RuntimeException{
    public CaseNotFoundException(String message) {
        super(message);
    }
}
