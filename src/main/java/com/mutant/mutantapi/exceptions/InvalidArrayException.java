package com.mutant.mutantapi.exceptions;

public class InvalidArrayException extends Exception {
    private static String defaultMessage = "Received an invalid DNA array";

    public InvalidArrayException () {
        super(defaultMessage);
    }

    public InvalidArrayException (String message) {
        super(message);
    }
}
