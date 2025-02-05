package com.grim.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.auth.util.JwtUtil;
import com.grim.token.model.dto.RefreshTokenDTO;
import com.grim.token.model.mapper.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	@Override
	public Map<String, String> generateToken(String username, Long userNo) {
		
		Map<String, String> tokens = createTokens(username);
		
		saveToken(tokens.get("refreshToken"), userNo);
		
		deleteExpiredRefreshToken(userNo);
		return tokens;
	}
	
	private void deleteExpiredRefreshToken(Long userNo) {
		Map<String, Long> params = new HashMap();
		params.put("userNo", userNo);
		params.put("currentTime", System.currentTimeMillis());
		
		tokenMapper.deleteExpiredRefreshToken(params);
	}
	
	private Map<String, String> createTokens(String username){
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}
	
	private void saveToken(String refreshToken, Long userNo) {
		
		RefreshTokenDTO token = RefreshTokenDTO.builder()
											   .token(refreshToken)
											   .userNo(userNo)
											   .expiration(System.currentTimeMillis() + 3600000L * 72)
											   .build();
		
		tokenMapper.saveToken(token);
		
	}

	@Override
	public Map<String, String> refreshTokens(String refreshToken) {
		RefreshTokenDTO token = tokenMapper.findByToken(refreshToken);
		
		if(token == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("알 수 없는 리프레시 토큰이");
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		return generateToken(user.getUsername(), user.getUserNo());
	}

}
