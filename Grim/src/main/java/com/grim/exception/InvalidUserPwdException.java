package com.grim.exception;

public class InvalidUserPwdException extends RuntimeException {
	
    public InvalidUserPwdException(String message) {
        super(message);
    }
}