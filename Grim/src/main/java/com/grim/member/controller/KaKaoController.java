package com.grim.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao/login")
public class KaKaoController {
	
	@Value("${kakao.client_id}")
	private String clientId;
	
	@Value("${kakao.redirect_uri}")
	private String redirectUri;
	
	

}
