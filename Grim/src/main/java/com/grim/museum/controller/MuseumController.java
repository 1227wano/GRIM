package com.grim.museum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grim.museum.model.service.MuseumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MuseumController {
	
	private MuseumService service;
	
	@GetMapping("/apiMuseum")
	public ResponseEntity<?> getApiMuseum(@RequestParam(name="page") int page){
		
		String response = service.getApiMuseum(page);
		System.out.println(response);
		return ResponseEntity.ok(response);
	}

}
