package com.grim.museum.model.service;

import java.util.List;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.museum.model.dto.MuseumDTO;
import com.grim.paint.model.dto.LikeDTO;

public interface MuseumService {

	String getApiMuseum(int page);

	String getRealMuseum(int page);

	void saveMuseum(MuseumDTO museum);

	List<MuseumDTO> getAllMuseum();

	MuseumDTO getMuseumDetail(Long userNo);

	MuseumDTO getMyMuseum(CustomUserDetails user);

	void updateMuseum(MuseumDTO museum);

	void deleteMuseum();

	List<LikeDTO> getHallOfFame();

}
