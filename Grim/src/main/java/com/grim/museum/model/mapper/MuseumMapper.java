package com.grim.museum.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.museum.model.dto.MuseumDTO;
import com.grim.paint.model.dto.LikeDTO;

@Mapper
public interface MuseumMapper {

	//@Insert("INSERT INTO TB_MUSEUM VALUES (#{museumName}, #{museumSidoName}, #{museumOpen}, #{museumClose})")
	void saveMuseum(MuseumDTO museum);

	List<MuseumDTO> getAllMuseum();

	MuseumDTO getMuseumDetail(Long userNo);

	MuseumDTO getMyMuseum(CustomUserDetails user);
	
	void updateMuseum(MuseumDTO museum);

	@Delete("DELETE TB_MUSEUM WHERE USER_NO = #{userNo}")
	void deleteMuseum(CustomUserDetails user);

	//@Select("SELECT BOARD_NO, COUNT(*) AS "like" FROM TB_PAINT_LIKE GROUP BY BOARD_NO ORDER BY "like" DESC")
	List<LikeDTO> getHallOfFame();

}
