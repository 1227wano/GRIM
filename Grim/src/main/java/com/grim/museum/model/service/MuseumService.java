package com.grim.museum.model.service;

import com.grim.museum.model.dto.MuseumDTO;

public interface MuseumService {

	String getApiMuseum(int page);

	String getRealMuseum(int page);

	void saveMuseum(MuseumDTO museum);

}
