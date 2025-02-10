package com.grim.exception;

public class InvalidParameterException extends RuntimeException{
	
	/**
	 * 유효하지않은 값 처리 
	 */
	public InvalidParameterException(String message) {
		super(message);
	}

}
