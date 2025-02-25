package com.grim.paint.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.grim.paint.model.dto.DeletePaintDTO;
import com.grim.paint.model.dto.LikeDTO;
import com.grim.paint.model.dto.PaintDTO;
import com.grim.paint.model.dto.SearchPaintDTO;

import jakarta.validation.Valid;

public interface PaintService {

	void save(@Valid PaintDTO board, MultipartFile file);

	List<SearchPaintDTO> findAll(int page);
	
    void update(PaintDTO board);
	
    void delete(Long id);
    
    PaintDTO findById(Long id);

	void like(LikeDTO board);
	
}
