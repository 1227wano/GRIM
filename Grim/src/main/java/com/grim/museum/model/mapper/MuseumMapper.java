package com.grim.museum.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.grim.museum.model.dto.MuseumDTO;

@Mapper
public interface MuseumMapper {

	//@Insert("INSERT INTO TB_MUSEUM VALUES (#{museumName}, #{museumSidoName}, #{museumOpen}, #{museumClose})")
	void saveMuseum(MuseumDTO museum);

	List<MuseumDTO> selectAllMuseum();

}
