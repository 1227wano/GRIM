package com.grim.exception;

public class InvalidUserEmailException extends RuntimeException {
	
    public InvalidUserEmailException(String message) {
        super(message);
    }
}
