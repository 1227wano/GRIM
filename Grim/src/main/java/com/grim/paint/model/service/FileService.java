package com.grim.paint.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Service
public class FileService {

    private final Path fileLocation;

    public FileService() {
        // 파일 저장 위치 설정
        this.fileLocation = Paths.get("http://localhost/upfiles/").toAbsolutePath().normalize();
    }

    public String store(MultipartFile file) {
        // 고유 파일 이름 생성
        String fileName = generateUniqueFileName(file.getOriginalFilename());
        Path targetLocation = this.fileLocation.resolve(fileName);
        try {
            Files.write(targetLocation, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
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
