package com.grim.museum.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.grim.museum.model.dto.MuseumDTO;

@Mapper
public interface MuseumMapper {

	@Insert("INSERT INTO TB_MUSEUM VALUES(#{museumName}, #{museumSidoname}, #{museumOpen}, #{museumClose})")
	void saveMuseum(MuseumDTO museum);

}
