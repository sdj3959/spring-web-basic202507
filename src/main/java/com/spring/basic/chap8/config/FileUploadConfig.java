package com.spring.basic.chap8.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

// 파일 업로드 관련 설정 클래스
@Getter
@Configuration
public class FileUploadConfig {

    // application.yml에 있는 파일업로드 루트경로를 읽어옴
    @Value("${file.upload.location}")
    private String location;

    // 루트 경로가 있는지 확인하고 없으면 디렉토리를 생성
    @PostConstruct      // 빈이 생성되는 순간 자동으로 1회 실행
    public void init() {
        File directory = new File(location);
        if (!directory.exists()) directory.mkdirs();
    }
}
