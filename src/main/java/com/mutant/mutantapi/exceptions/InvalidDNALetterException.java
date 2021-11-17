package com.mutant.mutantapi.exceptions;

public class InvalidDNALetterException extends Exception {
    private static String defaultMessage = "Found an invalid letter in the DNA sequence";

    public InvalidDNALetterException() {
        super(defaultMessage);
    }

    public InvalidDNALetterException(String message) {
        super(message);
    }
}
