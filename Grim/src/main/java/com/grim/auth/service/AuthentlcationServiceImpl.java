package com.grim.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grim.auth.model.vo.CustomUserDetails;
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
	public Map<String, String> login(MemberDTO requestMember) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(requestMember.getUserId(), requestMember.getUserPwd()));

		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		log.info("로그인절차 성공!");
		log.info("DB에서 조회된 사용자의 정보 {}", user);

		Map<String, String> tokens = tokenService.generateToken(user.getUsername(), user.getUserNo());
		
		return tokens;

	}
	
	public CustomUserDetails getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		return user;
	}
	
	public void validWriter(Long userNo, String username) {
		if (userNo == null || username == null || !username.equals(getAuthenticatedUser().getUsername())) {
            throw new RuntimeException("요청한 사용자와 게시글 작성자가 일치하지 않습니다.");
		}
	}
	

}
