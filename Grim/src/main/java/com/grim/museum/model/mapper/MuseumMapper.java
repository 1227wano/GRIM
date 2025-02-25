package com.grim.museum.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.museum.model.dto.MuseumDTO;

@Mapper
public interface MuseumMapper {

	//@Insert("INSERT INTO TB_MUSEUM VALUES (#{museumName}, #{museumSidoName}, #{museumOpen}, #{museumClose})")
	void saveMuseum(MuseumDTO museum);

	List<MuseumDTO> getAllMuseum();

	MuseumDTO getMuseumDetail(Long userNo);

	MuseumDTO getMyMuseum(CustomUserDetails user);
	
	void updateMuseum(MuseumDTO museum);

}
