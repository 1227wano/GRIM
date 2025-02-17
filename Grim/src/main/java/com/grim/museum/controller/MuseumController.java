package com.grim.museum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grim.museum.model.dto.MuseumDTO;
import com.grim.museum.model.service.MuseumService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
@RequestMapping(value="museum", produces="application/json; charset=UTF-8")
public class MuseumController {
	
	private final MuseumService service;
	
	@GetMapping("/apiMuseum")
	public ResponseEntity<?> getApiMuseum(@RequestParam(name="page") int page){
		
		String response = service.getApiMuseum(page);
		System.out.println(response);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/realMuseum")
	public ResponseEntity<?> getRealMuseum(@RequestParam(name="page") int page){
		
		String response = service.getRealMuseum(page);
		System.out.println(response);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<String> saveMuseum(@RequestBody MuseumDTO museum){
		System.out.println(museum);
		service.saveMuseum(museum);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("성공");
	}
	

}
