package com.grim.auth.util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.grim.auth.service.UserServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
	
	private final JwtUtil tokenUtil;
	private final UserServiceImpl userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getRequestURI();
		String method = request.getMethod();
		log.info("{}", header);
		if(header.contains("/upfiles/") && method.equals("get")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		log.info("필터 :{}", authorization);

		if (authorization == null || !authorization.startsWith("Bearer ")) {
			log.error("authorization이 존재하지 않습니다.");
			filterChain.doFilter(request, response);
			return;
		}

		String token = authorization.split(" ")[1];

		try {

			Claims claims = tokenUtil.parseJwt(token);

			String username = claims.getSubject();

			log.info("토큰 주인 : {}", username);
			
			UserDetails userDetails = userService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
					);
	
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		} catch (ExpiredJwtException e) {
			log.info("AccessToken이 만료되었습니다.");
	
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Expired Token");
			return;
		} catch (JwtException e) {
			log.info("Token 검증에 실패했습니다.");
	
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("이상해");
			return;
		} 
		
		filterChain.doFilter(request, response);
	}
	
	
}
