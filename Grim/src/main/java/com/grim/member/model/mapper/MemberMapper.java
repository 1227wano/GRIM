package com.grim.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberUpdateDTO;
import com.grim.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	
	MemberDTO findByUserNo(Long userNo);
	Member findByUserId(String userId);
	Member findByUserName(String userName);
	
	void save(Member member);
	
	@Update("UPDATE TB_MEMBER SET USER_PWD=#{password} WHERE USER_NO=#{userNo}")
	void changePassword(Map<String, String> changeRequest);
	
	@Delete("DELETE FROM TB_MEMBER WHERE USER_NO=#{userNo}")
	void deleteByPassword(Long userNo);
	
	void changeInfo(MemberDTO exsitingMember);
	
	@Update("UPDATE TB_MEMBER SET USER_IMG=#{userFileUrl} WHERE USER_NO=#{userNo}")
	int changeImg(MemberUpdateDTO member);

}
