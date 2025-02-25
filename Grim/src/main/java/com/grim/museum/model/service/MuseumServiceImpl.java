package com.grim.museum.model.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.grim.auth.model.vo.CustomUserDetails;
import com.grim.auth.service.AuthentlcationService;
import com.grim.exception.InvalidParameterException;
import com.grim.museum.model.dto.MuseumDTO;
import com.grim.museum.model.mapper.MuseumMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService {
	
	private final MuseumMapper mapper;
    private final AuthentlcationService authService;
	
	// 미술관 세울 매물 API
	public String getApiMuseum(int page) {
		String requestUrl = "http://apis.data.go.kr/B190017/service/GetBankruptcyEstatesGoodsService2020081/getRealEstateList2020081";
			   requestUrl += "?serviceKey=EhfazFkGEi%2B9n0cRDjEs%2FHz7D8Bvej8fga6MidRbq5kjfXEeVcwgl8HTqJNwCYjbz6KmcaFNS9rMFf2ADRJw5g%3D%3D";
			   requestUrl += "&pageNo=" + page;
			   requestUrl += "&numOfRows=144";
			   requestUrl += "&resultType=json";
			   try {
				requestUrl += "&progStutNm=" + URLEncoder.encode("매각검토", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
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

	// 실제 미술관 API
	@Override
	public String getRealMuseum(int page) {
		String requestUrl = "https://api.vworld.kr/req/data";
			   requestUrl += "?service=data";
			   requestUrl += "&request=GetFeature";
			   requestUrl += "&page=" + page;
			   requestUrl += "&size=300";
			   requestUrl += "&data=LT_P_DGMUSEUMART";
			   requestUrl += "&key=0AE873A4-4246-3871-B49A-76BB7ADDEC56";
			   requestUrl += "&domain=localhost";
			   requestUrl += "&geomFilter=BOX(124.5,33.0,132.0,38.9)";	// 대한민국 위도경도 범위
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

	// 미술관 창설 신청
	@Override
	@Transactional
	public void saveMuseum(MuseumDTO museum) {
		CustomUserDetails user = authService.getAuthenticatedUser();
		museum.setUserNo(user.getUserNo());
		mapper.saveMuseum(museum);
	}

	// DB미술관 전부 조회
	@Override
	public List<MuseumDTO> getAllMuseum() {
		return mapper.getAllMuseum(); 
	}

	// DB미술관 상세 조회
	@Override
	public MuseumDTO getMuseumDetail(Long userNo) {
		MuseumDTO museum = mapper.getMuseumDetail(userNo);
		if(museum == null) {
			throw new InvalidParameterException("미술관을 창설한 유저가 아닙니다.");
		}
		return museum;
	}

	// 마이페이지 미술관 조회
	@Override
	public MuseumDTO getMyMuseum(CustomUserDetails user) {
		MuseumDTO museum = mapper.getMyMuseum(user);
		if(museum == null) {
			throw new InvalidParameterException("미술관을 창설한 유저가 아닙니다.");
		}
		return museum;
	}

	@Override
	public void updateMuseum(MuseumDTO museum) {
		CustomUserDetails user = authService.getAuthenticatedUser();
		museum.setUserNo(user.getUserNo());
		mapper.updateMuseum(museum);
		
	}

}
