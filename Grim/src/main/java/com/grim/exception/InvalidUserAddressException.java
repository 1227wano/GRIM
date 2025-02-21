package com.grim.exception;

public class InvalidUserAddressException extends RuntimeException {
	
    public InvalidUserAddressException(String message) {
        super(message);
    }
}
