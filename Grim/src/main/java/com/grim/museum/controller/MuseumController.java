package com.grim.museum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.museum.model.dto.MuseumDTO;
import com.grim.museum.model.service.MuseumService;
import com.grim.paint.model.dto.LikeDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@CrossOrigin("*")
@RequestMapping(value="museum", produces="application/json; charset=UTF-8")
public class MuseumController {
	
	private final MuseumService service;
	
	// 매물정보 가져오기
	@GetMapping("/apiMuseum")
	public ResponseEntity<?> getApiMuseum(@RequestParam(name="page") int page){
		
		String response = service.getApiMuseum(page);
		//System.out.println(response);
		return ResponseEntity.ok(response);
	}
	
	// 실제 미술관 가져오기 
	@GetMapping("/realMuseum")
	public ResponseEntity<?> getRealMuseum(@RequestParam(name="page") int page){
		
		String response = service.getRealMuseum(page);
		//System.out.println(response);
		return ResponseEntity.ok().body(response);
	}
	
	// 미술관 창설 신청
	@PostMapping
	public ResponseEntity<String> saveMuseum(@Valid @RequestBody MuseumDTO museum){ 
		System.out.println(museum);
		service.saveMuseum(museum);	
		
		return ResponseEntity.status(HttpStatus.CREATED).body("미술관 창설 신청이 완료되었습니다.");
	}
	
	// 창설된 미술관 가져오기
	@GetMapping
	public ResponseEntity<List<MuseumDTO>> getAllMuseum(){
		return ResponseEntity.ok(service.getAllMuseum()); 
	}
	
	// 미술관 상세보기
	@GetMapping("/{id}")	// id = museum.userNo
	public ResponseEntity<MuseumDTO> getMuseumDetail(@PathVariable(name="id") 
	 												@Min(value = 1, message="0보다 작을 수 없습니다.") Long userNo){
		return ResponseEntity.ok(service.getMuseumDetail(userNo));
	}
	
	// 명예의 전당
	@GetMapping("like")
	public ResponseEntity<List<LikeDTO>> getHallOfFame(){
		return ResponseEntity.ok(service.getHallOfFame());
	}
	
	
	
	
	
	
	
	// 마이페이지 미술관 상세보기
	@GetMapping("/myMuseum")
	public ResponseEntity<MuseumDTO> getMuseumDetail(@AuthenticationPrincipal CustomUserDetails user){
		return ResponseEntity.ok(service.getMyMuseum(user));
	}
	
	// 미술관 정보 수정
	@PutMapping
	public ResponseEntity<String> updateMuseum(@Valid @RequestBody MuseumDTO museum){ 
		System.out.println(museum);
		service.updateMuseum(museum);	
		
		return ResponseEntity.status(HttpStatus.CREATED).body("미술관 정보 수정이 완료되었습니다.");
	}
	
	// 미술관 정보 삭제
	@DeleteMapping
	public ResponseEntity<String> deleteMuseum(){
		System.out.println();
		service.deleteMuseum();
		
		return ResponseEntity.status(HttpStatus.CREATED).body("미술관이 삭제되었습니다.");
	}
	

}
