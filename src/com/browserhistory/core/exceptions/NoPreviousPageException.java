package com.browserhistory.core.exceptions;

public class NoPreviousPageException extends RuntimeException {
    public NoPreviousPageException(String message) {
        super(message);
    }
}