package com.spring.basic.chap8.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 사용자가 업로드한 파일 서버에 URL로 접근할 수 있게 해주는 설정
@Configuration
@RequiredArgsConstructor
public class WebResourceConfig implements WebMvcConfigurer {

    private final FileUploadConfig fileUploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  파일 서버에 저장된 C:/Users/user/spring/upload 안에 들어있는 파일들을
         *  백엔드 서버에서 http://localhost:9000/uploads/파일명 -> 했을 때 꺼내주겠다
         */
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:"+fileUploadConfig.getLocation());
    }
}
