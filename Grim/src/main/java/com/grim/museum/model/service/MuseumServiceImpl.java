package com.grim.museum.model.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService {
	
	public String getApiMuseum(int page) {
		
		String requestUrl = "http://apis.data.go.kr/B190017/service/GetBankruptcyEstatesGoodsService2020081";
			   requestUrl += "?serviceKey=EhfazFkGEi%2B9n0cRDjEs%2FHz7D8Bvej8fga6MidRbq5kjfXEeVcwgl8HTqJNwCYjbz6KmcaFNS9rMFf2ADRJw5g%3D%3D";
			   requestUrl += "&numOfRows=10";
			   requestUrl += "&pageNo=" + page;
			   requestUrl += "&resultType=json";
			  
		URI uri = null;
		try {
			uri = new URI(requestUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(uri, String.class);
		
		return response;
	}

}
