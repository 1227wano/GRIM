package com.grim.exception;

public class InvalidUserNameException extends RuntimeException {
    
	public InvalidUserNameException(String message) {
        super(message);
    }
}