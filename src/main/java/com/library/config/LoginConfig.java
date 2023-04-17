package com.library.config;

import com.library.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer {

    // 개발 시점에 static directory 경로 읽는 코드 (JS, CSS)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

//    // 배포 시점에 사용 가능한 코드 (배포 서버 경로를 정확히 입력해야 함)
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/files/**")
//                .addResourceLocations("file:/opt/files/");
//
//        // 윈도우라면
//         .addResourceLocations(“file:///C:/opt/files/“);
//    }

    // 인터셉터 로그인 ( ADMIN, MEMBER )
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                // 접근 불가 Page 추가
                .addPathPatterns("/manager/**")
                // 접근 불가 페이지 제외
                .excludePathPatterns("/manager", "/api/members/login", "/admin/**");

    }
}
