package com.grim.token.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.grim.token.model.dto.RefreshTokenDTO;

@Mapper
public interface TokenMapper {
	
	@Insert("INSERT INTO TB_TOKEN VALUES(#{userNo}, #{token}, #{expiration})")
	void saveToken(RefreshTokenDTO refreshToken);

	@Select("SELECT USER_NO userNo, TOKEN token, EXPIRATION expiration FROM TB_TOKEN WHERE TOKEN=#{refreshToken}")
	RefreshTokenDTO findByToken(String refreshToken);
	
	void deleteExpiredRefreshToken(Map<String, Long> params);
	

}
