package com.grim.exception;

public class DuplicateUserException extends RuntimeException {
	
	/**
	 * 아이디 중복값 체크하는 메소드
	 */
	public DuplicateUserException(String message) {
		super(message); 
	}

}
