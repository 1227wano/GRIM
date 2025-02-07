package com.grim.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grim.auth.service.AuthentlcationServiceImpl;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.service.MemberService;
import com.grim.member.model.vo.LoginResponse;
import com.grim.token.model.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="members", produces="application/json; charset=UTF-8")
public class MemberController {

	private final MemberService memberService;
	private final AuthentlcationServiceImpl authService;
	private final TokenService tokenService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> save(@Valid @RequestBody MemberDTO requestMember) {
		log.info("리엑트에서 넘어온값 :{}", requestMember);
		memberService.save(requestMember);
		
		return ResponseEntity.ok("회원가입 성공");
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MemberDTO requestMember){
		log.info(" 컨트롤러~ {}",requestMember);
		Map<String, String> tokens = authService.login(requestMember);
		
		LoginResponse response = LoginResponse.builder().username(requestMember.getUserId()).tokens(tokens).build();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changeEntity){
		
		memberService.changePassword(changeEntity);
		
		return ResponseEntity.ok("수정완료");
	}
	
	
	
	
	 
	
	
	
	
	
	
	
	
}