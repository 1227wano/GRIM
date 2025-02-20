package com.grim.member.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.grim.member.model.dto.KoKoaDTO;
import com.grim.token.model.dto.KakaoTokenResponse;

@RestController
public class KaKaoController {

    

    private final String clientId = "22037b1000d672d856d5c603e656c581";
    private final String redirectUri = "http://localhost:3000/kakao/callback";

    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam String code) {
        

        RestTemplate restTemplate = new RestTemplate();
        String accessTokenUrl = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&code=" + code;
        KakaoTokenResponse tokenResponse = restTemplate.postForObject(accessTokenUrl, null, KakaoTokenResponse.class);

        if (tokenResponse != null) {
        

            String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + tokenResponse.getAccess_token());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<KoKoaDTO> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, KoKoaDTO.class);

            return response.getBody().toString();
        } else {
        
            return "Failed to receive access token.";
        }
    }
}

