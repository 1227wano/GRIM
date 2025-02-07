package com.grim.exception.handler;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.grim.exception.DuplicateUserException;
import com.grim.exception.JwtTokenException;
import com.grim.exception.MissmatchPasswordException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 아이디 중복체크 처리
	 */
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<?> handleDuplicateUser(DuplicateUserException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	/**
	 * 비밀번호 불일치 처리 
	 */
	@ExceptionHandler(MissmatchPasswordException.class)
	public ResponseEntity<?> handleMissmatchPassword(MissmatchPasswordException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	/**
	 * 유효하지 않은 값 처리 
	 */
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<?> handleInvalidParameter(InvalidParameterException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	/**
	 * 트큰 검증 처리 
	 */
	@ExceptionHandler(JwtTokenException.class)
	public ResponseEntity<?> handleInvalidToken(JwtTokenException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}
	
	/**
	 * 벨리데이션 예외 처리하는 인셉션 
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNoValid(MethodArgumentNotValidException e){
		
		Map<String, String> errors = new HashMap();
		
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
	
}
