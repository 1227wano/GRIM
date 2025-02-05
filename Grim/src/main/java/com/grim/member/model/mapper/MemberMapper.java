package com.grim.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.grim.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	
	Member findByUserId(String userNo);

}
