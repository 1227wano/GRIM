package com.grim.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.exception.MissmatchPasswordException;
import com.grim.member.model.dto.MemberDTO;
import com.grim.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthentlcationServiceImpl implements AuthentlcationService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Map<String, Object> login(MemberDTO requestMember) {
		
		Map<String, Object> response = new HashMap<>();
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(requestMember.getUserId(), requestMember.getUserPwd()));
			
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
	
		log.info("로그인절차 성공!");
		log.info("DB에서 조회된 사용자의 정보 {}", user);
	
		Map<String, String> tokens = tokenService.generateToken(user.getUsername(), user.getUserNo());
			
		response.put("UserNo", user.getUserNo());
		response.put("username", user.getUsername());
		response.put("tokens", tokens);
		
		return response;

	}
	
	public CustomUserDetails getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		return user;
	}
	
	public void validWriter(String writer, String username) {
		if(writer != null && !writer.equals(username)) {
			throw new RuntimeException("요청한 사용자와 게시글 작성자가 일치하지 않습니다.");
		}
	}
	

}
