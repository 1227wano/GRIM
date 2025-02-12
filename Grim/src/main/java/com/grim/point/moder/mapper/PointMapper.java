package com.grim.point.moder.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.grim.point.moder.dto.PointDTO;
@Mapper
public interface PointMapper {
	
	@Insert("INSERT INTO TB_POINT VALUES (SEQ_USER.CURRVAL, 1000, '회원가입 포인트 지급', SYSDATE)")
	void newSave();
	
	/**
	 * 사용자 번호로 포인트 정보 조회하는 기능
	 * @return 
	 */
	@Select("SELECT USER_NO, POINT, REASON, REASON_DATE FROM TB_POINT WHERE USER_NO=#{userNo}")
	PointDTO findByPointNo(Long userNo);
	

}
