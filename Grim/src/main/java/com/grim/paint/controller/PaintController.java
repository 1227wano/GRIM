package com.grim.paint.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grim.paint.model.dto.PaintDTO;
import com.grim.paint.model.dto.SearchPaintDTO;
import com.grim.paint.model.service.PaintService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/paint")
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class PaintController {
	
	private final PaintService service;
	
	@PostMapping
	public ResponseEntity<?> save(@ModelAttribute @Valid PaintDTO board, @RequestParam(name="file", required = false) MultipartFile file){
		log.info("board = {} / file = {}", board, file);
		service.save(board,file);

		return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
	}
	
	@GetMapping
	public ResponseEntity<List<SearchPaintDTO>> findAll(@RequestParam(name="page", defaultValue="0")int page){
        log.info("page = {}", page);
        List<SearchPaintDTO> list = service.findAll(page);
        log.info("list = {}", list);
		return ResponseEntity.ok(list);
	}

}
