package com.grim.point.moder.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PointMapper {
	@Insert("INSERT INTO TB_POINT VALUES (SEQ_USER.CURRVAL, 1000, '회원가입 포인트 지급', SYSDATE)")
	void newSave();
	

}
