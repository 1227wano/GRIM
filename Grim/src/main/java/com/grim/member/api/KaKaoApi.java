package com.grim.member.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kakao")
public class KaKaoApi {
	
	@Value("${kakao.client_id}")
	private String clientId;
	
	@Value("${kakao.redirect_uri}")
	private String redirectUri;
	
	@GetMapping("/kakao/login")
	public String loginPage(Model model) {
		String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUri;
		
		model.addAttribute("location", location);
		
		return "login";
	}
	
	

}
