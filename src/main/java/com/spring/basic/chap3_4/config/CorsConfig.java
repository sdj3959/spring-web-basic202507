package com.spring.basic.chap3_4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 전역 크로스 오리진 설정 : 특정 클라이언트가 API를 사용하기 위한 정책 설정
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 요청을 허용할 서버의 URL
                .addMapping("/api/v3-3/*")
                // 어떤 클라이언트를 허용할 것인지
                .allowedOrigins("https://127.0.0.1:5500")
                // 어떤 요청 방식을 허용할지
                .allowedMethods("GET")
                .allowedHeaders("*")
        ;
    }
}
