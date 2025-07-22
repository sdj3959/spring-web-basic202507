package com.spring.basic.chap8.api;

import com.spring.basic.chap8.config.FileUploadConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// 사용자가 올린 첨부파일 (이미지, 영상 등) 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/api/v8")
@Slf4j
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadConfig fileUploadConfig;

    // 첨부파일 업로드 요청 처리
    @PostMapping("/uploads")
    public ResponseEntity<?> uploadSingleFile(
            @RequestParam("profile") MultipartFile originFile
    ) {

        log.info("uploaded file name: {}", originFile.getOriginalFilename());
        log.info("uploaded file type: {}", originFile.getContentType());

        // 파일을 받았으면 잘 보관 (로컬에 저장)

        // 저장 파일 경로 생성
        // 루트 디렉토리 가져오기
        String rootDir = fileUploadConfig.getLocation();
        // 파일명 가져오기
        String fileName = originFile.getOriginalFilename();
        String fullName = rootDir + fileName;

        File uploadedLocation = new File(fullName);

        // 파일 전송 명령
        try {
            originFile.transferTo(uploadedLocation);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("파일 저장 실패!");
        }

        return ResponseEntity.ok("OK!!");
    }
}
