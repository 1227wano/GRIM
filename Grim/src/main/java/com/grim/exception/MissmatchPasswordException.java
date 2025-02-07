package com.grim.exception;

public class MissmatchPasswordException extends RuntimeException {
	
	/**
	 * 비밀번호 불일치 예외처리
	 */
	public MissmatchPasswordException(String message) {
		super(message);
	}

}
