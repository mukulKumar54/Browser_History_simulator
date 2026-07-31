package com.browserhistory.core.exceptions;

public class NoClosedTabsException extends RuntimeException {
    public NoClosedTabsException(String message) {
        super(message);
    }
}