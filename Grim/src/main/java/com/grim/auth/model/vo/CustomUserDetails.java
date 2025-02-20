package com.grim.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class CustomUserDetails implements UserDetails {
	private Long userNo;
	private String username;
	private String password;
	private String nickName;
	private String address;
	private String email;
	private int point;
	private String userImg;
	private Collection<? extends GrantedAuthority> authorities;

}
