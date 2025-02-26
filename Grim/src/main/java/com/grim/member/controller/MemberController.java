package com.grim.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.auth.service.AuthentlcationServiceImpl;
import com.grim.member.model.dto.ChangePasswordDTO;
import com.grim.member.model.dto.MemberDTO;
import com.grim.member.model.dto.MemberInfoResponseDTO;
import com.grim.member.model.dto.MemberUpdateDTO;
import com.grim.member.model.service.MemberService;
import com.grim.member.model.vo.LoginResponse;
import com.grim.token.model.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
		//log.info("리엑트에서 넘어온값 :{}", requestMember);
		memberService.save(requestMember);
		
		return ResponseEntity.ok("회원가입 성공");
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody MemberDTO requestMember, HttpServletRequest userRequest){
		
		Map<String, Object> loginResponse = authService.login(requestMember);
		
		log.info("로그인 정보:  {}", loginResponse);

		LoginResponse response = LoginResponse.builder()
				.userNo((Long) loginResponse.get("userNo"))
				.username((String) loginResponse.get("username"))
				.userImg((String) loginResponse.get("userImg"))
				.tokens((Map<String, String>) loginResponse.get("tokens"))
				.build();
		
	
		
		return ResponseEntity.ok(response);
	}
	

	
	@PutMapping("/mypage/update")
	public ResponseEntity<?> changeInfo(@ModelAttribute MemberUpdateDTO member,
										@RequestParam(name="file", required=false) MultipartFile file){
		
		log.info("업데이트 해보자고~ : {}", member);
		log.info("업데이트 사진이라고 : {}", file);
		
		MemberDTO updated = memberService.changeInfo(member, file);
		
		
		return ResponseEntity.ok(updated);
	}
	
	@PutMapping("/mypage/imgupdate")
	public ResponseEntity<?> changeImg(@RequestBody MemberUpdateDTO member){
				
		return ResponseEntity.ok().body(memberService.changeImg(member));
	}
	
	
	@PutMapping("/mypage/password")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changeEntity){
		
		memberService.changePassword(changeEntity);
		
		return ResponseEntity.ok("수정완료");
	}
	
	@GetMapping("/mypage/info")
	public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal CustomUserDetails user){
		
		MemberInfoResponseDTO member = memberService.getMyInfo(user);
		
		log.info("내정보 조회 : {}", member);
		return ResponseEntity.ok(member);
		
	}
	
	@DeleteMapping("/mypage/leave")
	public ResponseEntity<String> deleteByPassword(@RequestBody Map<String, String> password){
		
		memberService.deleteByPassword(password.get("password"));
		
		return ResponseEntity.ok("삭제 완료");
	}
	
	
	 
	
	
	
	
	
	
	
	
}