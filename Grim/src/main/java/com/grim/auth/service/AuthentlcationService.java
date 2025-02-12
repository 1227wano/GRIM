package com.grim.auth.service;

import java.util.Map;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.member.model.dto.MemberDTO;

public interface AuthentlcationService {
	
	Map<String, Object> login(MemberDTO requestMember);
	
	CustomUserDetails getAuthenticatedUser();
	
	void validWriter(Long userNo, String username);

}
