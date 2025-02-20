package com.grim.member.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberInfoResponseDTO;
import com.grim.member.model.dto.MemberUpdateDTO;

import jakarta.validation.Valid;

public interface MemberService {

	void save(MemberDTO requestMember);

	void changePassword(ChangePasswordDTO changeEntity);

	MemberInfoResponseDTO getMyInfo(CustomUserDetails user);

	void deleteByPassword(String password);

	MemberDTO changeInfo(MemberUpdateDTO member, MultipartFile file);

	Object changeImg(MemberUpdateDTO member);


}
 