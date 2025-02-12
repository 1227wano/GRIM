package com.grim.exception;

public class DuplicateNameException extends RuntimeException {

	/**
	 * 별명 중복값 체크하는 메소드
	 * 아이디는 로그인할때 식별 별명은 사용자들간에 편리성
	 */
	public DuplicateNameException(String message) {
		super(message); 
	}

}
