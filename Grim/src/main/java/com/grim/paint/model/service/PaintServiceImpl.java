package com.grim.paint.model.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.auth.service.AuthentlcationService;
import com.grim.paint.model.dto.DeletePaintDTO;
import com.grim.paint.model.dto.LikeDTO;
import com.grim.paint.model.dto.PaintDTO;
import com.grim.paint.model.dto.PaintPicDTO;
import com.grim.paint.model.dto.SearchPaintDTO;
import com.grim.paint.model.mapper.PaintMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaintServiceImpl implements PaintService {

	private final PaintMapper mapper;
	private final FileService fileService;
	private final AuthentlcationService authService;

	@Override
	@Transactional
	public void save(PaintDTO board, MultipartFile file) {
		CustomUserDetails user = authService.getAuthenticatedUser();
		board.setUserNo(user.getUserNo());
		mapper.savePaintBoard(board);

		if (file != null && !file.isEmpty()) {
			String fileName = fileService.store(file);  
			String fileDownloadUri = "http://localhost/upfiles/" + fileName;
			// log.info("filename = {}", fileName);
			PaintPicDTO paintPicDto = new PaintPicDTO();
			paintPicDto.setPicName(fileDownloadUri);  
			paintPicDto.setPicBoardNo(board.getPicBoardNo());
			mapper.savePaint(paintPicDto);
		}
	}

	@Override
	public List<SearchPaintDTO> findAll(int page) {
		int size = 15;
		RowBounds rowBounds = new RowBounds(page * size, size);
		List<SearchPaintDTO> list = mapper.findAll(rowBounds);
		log.info("list = {}", list);
		return list;
	}

	@Override
	@Transactional
	public void update(PaintDTO board) {

		CustomUserDetails user = authService.getAuthenticatedUser();
		long authenticatedUserNo = user.getUserNo();
		PaintDTO existingBoard = mapper.findById(board.getPicBoardNo());

		if (existingBoard != null && existingBoard.getUserNo() == authenticatedUserNo) {
			board.setUserNo(authenticatedUserNo);
			mapper.updatePaintBoard(board);
		} else {
			throw new RuntimeException("수정 권한이 없습니다.");
		}
	}

	@Override
	@Transactional
	public PaintDTO findById(Long id) {
	    return mapper.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	    CustomUserDetails user = authService.getAuthenticatedUser();
	    long authenticatedUserNo = user.getUserNo();
	    DeletePaintDTO existingBoard = mapper.deleteById(id);
	    log.info("existingBoard = {}", existingBoard.getUserNo());
	    log.info("authenticatedUserNo = {}", authenticatedUserNo);
	    if (existingBoard != null && existingBoard.getUserNo() == authenticatedUserNo) {
			mapper.deletePaintBoard(id);
	    } else {
	        throw new RuntimeException("삭제 권한이 없습니다.");
	    }

}

	@Override
	public void like(LikeDTO board) {
		CustomUserDetails user = authService.getAuthenticatedUser();
		board.setUserNo(user.getUserNo());
		mapper.like(board);
	}
}


