package com.grim.exception.handler;

import java.security.InvalidParameterException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grim.exception.DuplicateUserException;
import com.grim.exception.MissmatchPasswordException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 아이디 중복체크 예외처리
	 */
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<?> handleDuplicateUser(DuplicateUserException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	/**
	 * 비밀번호 불일치 예외처리 
	 */
	@ExceptionHandler(MissmatchPasswordException.class)
	public ResponseEntity<?> handleMissmatchPassword(MissmatchPasswordException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<?> handleInvalidParameter(InvalidParameterException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
