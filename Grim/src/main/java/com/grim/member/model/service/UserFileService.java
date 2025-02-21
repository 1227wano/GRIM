package com.grim.member.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserFileService {
	
	private final Path fileLocaton;
	
	public UserFileService() {
		this.fileLocaton = Paths.get("upfiles").toAbsolutePath().normalize();
	}
	
	public String store(MultipartFile file) {
		
		
		
		// 파일 가져오기
		String fileName = "user_img_" + Paths.get(file.getOriginalFilename()).getFileName().toString();
		
		// 저장 위치 지정
		Path targetLocation = this.fileLocaton.resolve(fileName);
		
		
		// 저장(복사)
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return "http://localhost/upfiles/" + fileName;
		} catch (IOException e) {
			throw new RuntimeException("파일을 찾을 수 없습니다.");
		}
	}
	

}
