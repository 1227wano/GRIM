package com.grim.paint.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grim.paint.model.dto.LikeDTO;
import com.grim.paint.model.dto.PaintDTO;
import com.grim.paint.model.dto.SearchPaintDTO;
import com.grim.paint.model.service.FileService;
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
    private final FileService fileService;
    @PostMapping
    public ResponseEntity<?> save(@ModelAttribute @Valid PaintDTO board, @RequestParam(name="file", required = false) MultipartFile file){
        log.info("board = {} / file = {}", board, file);
        service.save(board, file);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
    }

    
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(@RequestParam(name="page", defaultValue="0") int page) {
        log.info("page = {}", page);
        List<SearchPaintDTO> list = service.findAll(page);
        log.info("list = {}", list);

        int totalRecords = service.getTotalRecords(); // 전체 레코드 수를 얻는 메서드
        int totalPages = (int) Math.ceil((double) totalRecords / 15);

        Map<String, Object> response = new HashMap<>();
        response.put("list", list);
        response.put("totalPages", totalPages);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @ModelAttribute @Valid PaintDTO board){
        log.info("board = {}", board);
        board.setPicBoardNo(id);
        service.update(board);
        return ResponseEntity.ok("게시글 수정 성공");
    } 
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok("게시글 수정 성공");
    } 
    
    @PostMapping
    public ResponseEntity<?> like(@ModelAttribute @Valid LikeDTO board){
        log.info("board = {}", board);
        service.like(board);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
    }
    
}

