package com.grim.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.exception.DuplicateNameException;
import com.grim.exception.DuplicateUserException;
import com.grim.exception.MissmatchPasswordException;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberInfoResponseDTO;
import com.grim.member.model.mapper.MemberMapper;
import com.grim.member.model.vo.Member;
import com.grim.point.moder.dto.PointDTO;
import com.grim.point.moder.mapper.PointMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private final PointMapper pointMapper;
	
	
	@Override
	@Transactional
	public void save(MemberDTO requestMember) {
		
		Member searched = memberMapper.findByUserId(requestMember.getUserId());
		
		if(searched != null) {
			throw new DuplicateUserException("❌ 이미 존재하는 아이디 입니다.");
		} 
		
		Member nameSearched = memberMapper.findByUserName(requestMember.getUserName());
		
		if(nameSearched != null) {
			throw new DuplicateNameException("❌ 이미 존재하는 별명 입니다.");
		}
		
		Member member = Member.builder()
						.userId(requestMember.getUserId())
						.userPwd(passwordEncoder.encode(requestMember.getUserPwd()))
						.userName(requestMember.getUserName())
						.userAddress(requestMember.getUserAddress())
						.userEmail(requestMember.getUserEmail())
						.role("ROLE_USER")
						.build();
		
		memberMapper.save(member);
		pointMapper.newSave();
		
	}

	@Override
	public void changePassword(ChangePasswordDTO changeEntity) {
		
		log.info("서비스 검증 보자~ : {}", changeEntity);
		
		if(!changeEntity.getNewPassword().equals(changeEntity.getNewPasswordCheck())) {
			throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		
		Long userNo = passwordMatches(changeEntity.getCurrentPassword());
		
		String encodedPassword = passwordEncoder.encode(changeEntity.getNewPassword());
		
		Map<String, String> changeRequest = new HashMap();
		changeRequest.put("userNo", String.valueOf(userNo));
		changeRequest.put("password", encodedPassword);
		
		memberMapper.changePassword(changeRequest);
		
	}
	
	
	
	private Long passwordMatches(String password) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDatails = (CustomUserDetails)auth.getPrincipal();
		if(!passwordEncoder.matches(password, userDatails.getPassword())) {
			throw new MissmatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		return userDatails.getUserNo();
	}
	
	@Override
	public MemberInfoResponseDTO getMyInfo(CustomUserDetails user) {
		
		MemberDTO member = memberMapper.findByUserNo(user.getUserNo());
		PointDTO point = pointMapper.findByPointNo(user.getUserNo());
		log.info("내정보 확인 : {}, 포인트 확인 :{}", member, point);
		
		
		return new MemberInfoResponseDTO(member, point);
		
		
		
	}
	
				
	
	

}
