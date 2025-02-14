package com.grim.member.model.service;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberInfoResponseDTO;

public interface MemberService {

	void save(MemberDTO requestMember);

	void changePassword(ChangePasswordDTO changeEntity);

	MemberInfoResponseDTO getMyInfo(CustomUserDetails user);

	void deleteByPassword(String password);


}
 