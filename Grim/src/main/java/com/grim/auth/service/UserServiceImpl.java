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
		
		log.info("여기서 막히면 보통 DTO 검증 문제야 {}", user);
		if(user == null) {
			throw new UsernameNotFoundException("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}

		return CustomUserDetails.builder()
								.userNo(user.getUserNo())
								.username(user.getUserId())
								.password(user.getUserPwd())
								.nickName(user.getUserName())
								.address(user.getUserAddress())
								.email(user.getUserEmail())
								.userImg(user.getUserFileUrl())
								.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
								.build();
	}
	
}
