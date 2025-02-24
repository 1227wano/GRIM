package com.grim.paint.model.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService { 

    private final Path fileLocation;

    public FileService() {
        this.fileLocation = Paths.get("upfiles").toAbsolutePath().normalize();  
    }

    public String store(MultipartFile file) {
        String fileName = generateUniqueFileName(file.getOriginalFilename()); 
        Path targetLocation = this.fileLocation.resolve(fileName);
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("빈 파일을 저장할 수 없습니다.");
            }

            log.info("저장 경로: {}", targetLocation.toString());
            Files.copy(file.getInputStream(), targetLocation);
            log.info("파일 저장 완료: {}", targetLocation.toString());

        } catch (IOException e) {
            log.error("파일 저장 실패: {}", e.getMessage());
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
        return fileName;  
    }

    private String generateUniqueFileName(String originalFileName) {
        String timestamp = String.valueOf(new Date().getTime());
        String randomString = UUID.randomUUID().toString().substring(0, 10);
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return timestamp + "_" + randomString + fileExtension;
    }
}
