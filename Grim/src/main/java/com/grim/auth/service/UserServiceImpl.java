package com.grim.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.member.model.mapper.MemberMapper;
import com.grim.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService {
	
	private final MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("userService : {}", username);
		Member user = mapper.findByUserId(username);
		
		log.info("요까진오자너?");
		if(user == null) {
			throw new UsernameNotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		// 사용자가 입력한 아이디값이 테이블에 존재하긴 함
		return CustomUserDetails.builder()
								.userNo(user.getUserNo())
								.username(user.getUserId())
								.password(user.getUserPwd())
								.nickName(user.getUserName())
								.address(user.getUserAddress())
								.email(user.getUserEmail())
								.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
								.build();
	}
	
}
