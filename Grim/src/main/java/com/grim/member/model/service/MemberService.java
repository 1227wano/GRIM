package com.grim.member.model.service;

import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;

public interface MemberService {

	void save(MemberDTO requestMember);

	void changePassword(ChangePasswordDTO changeEntity);

}
 