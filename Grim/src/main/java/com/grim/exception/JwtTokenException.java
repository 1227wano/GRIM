package com.grim.exception;

public class JwtTokenException extends RuntimeException {
	
	/**
	 * 토큰 검증 처리 
	 */
	public JwtTokenException(String message) {
		super(message);
	}
}
