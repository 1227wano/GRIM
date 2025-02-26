package com.grim.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.auth.util.MemberValidator;
import com.grim.exception.DuplicateNameException;
import com.grim.exception.DuplicateUserException;
import com.grim.exception.MissmatchPasswordException;
import com.grim.exception.UsernameNotFoundException;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberInfoResponseDTO;
import com.grim.member.model.dto.MemberUpdateDTO;
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
	private final UserFileService fileService;
	
	
	
	
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
		
		
		
		if(!changeEntity.getNewPassword().equals(changeEntity.getNewPasswordCheck())) {
			throw new MissmatchPasswordException("❌ 비밀번호가 일치하지 않습니다.");
		}
		
		Long userNo = passwordMatches(changeEntity.getCurrentPassword());
		
		String encodedPassword = passwordEncoder.encode(changeEntity.getNewPassword());
		
		Map<String, String> changeRequest = new HashMap();
		changeRequest.put("userNo", String.valueOf(userNo));
		changeRequest.put("password", encodedPassword);
		
		memberMapper.changePassword(changeRequest);
		
	}
	
	
	
	/**
	 * 비밀번호 나한테 맡겨!
	 * 내가 검증해줄게~
	 */
	private Long passwordMatches(String password) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDatails = (CustomUserDetails)auth.getPrincipal();
		if(!passwordEncoder.matches(password, userDatails.getPassword())) {
			throw new MissmatchPasswordException("❌ 비밀번호가 일치하지 않습니다.");
		}
		return userDatails.getUserNo();
	}
	
	@Override
	public MemberInfoResponseDTO getMyInfo(CustomUserDetails user) {
		
		MemberDTO member = memberMapper.findByUserNo(user.getUserNo());
		PointDTO point = pointMapper.findByPointNo(user.getUserNo());
		
		
		
		return new MemberInfoResponseDTO(member, point);
		
		
		
	}

	@Override
	public void deleteByPassword(String password) {
		Long userNo = passwordMatches(password);
		 
		memberMapper.deleteByPassword(userNo);
	}

	@Override
	public MemberDTO changeInfo(MemberUpdateDTO member, MultipartFile file) {
		
		MemberDTO exsitingMember = getUserOrThrow(member.getUserNo());
			
		Member nameSearched = memberMapper.findByUserName(member.getUserName());
		
		if (member.getUserName() != null && !member.getUserName().isEmpty() && !member.getUserName().equals("undefined")) {
			MemberValidator.validateUserName(member.getUserName());
			exsitingMember.setUserName(member.getUserName());
			
			if(nameSearched != null) {
				throw new DuplicateNameException("❌ 이미 존재하는 별명 입니다.");
			} 
	    }

	    if (member.getUserAddress() != null && !member.getUserAddress().isEmpty() && !member.getUserAddress().equals("undefined")) {
	        MemberValidator.validateUserAddress(member.getUserAddress());
	    	exsitingMember.setUserAddress(member.getUserAddress());
	    }

	    if (member.getUserEmail() != null && !member.getUserEmail().isEmpty() && !member.getUserEmail().equals("undefined")) {
	        MemberValidator.validateUserEmail(member.getUserEmail());
	    	exsitingMember.setUserEmail(member.getUserEmail());
	    }
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			exsitingMember.setUserFileUrl(filePath);
			
		}
		
		memberMapper.changeInfo(exsitingMember);
		
		return exsitingMember;
	}
	
	private MemberDTO getUserOrThrow(Long userNo) {
		MemberDTO member = memberMapper.findByUserNo(userNo);
		if(member == null) {
			throw new UsernameNotFoundException("존재하지 않는 유저 입니다.");
		}
		return member;
	}

	@Override
	public Object changeImg(MemberUpdateDTO member) {
		return memberMapper.changeImg(member);
	}
	
				
	
	

}
