package com.library.config;

import com.library.interceptor.LoginCheckInterceptorMember;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LoginConfigMember implements WebMvcConfigurer {

    // 인터셉터 로그인
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptorMember())
                // 접근 불가 Page 추가
                // 인터셉터를 적용할 페이지
                .addPathPatterns(
                        "/board/write",
                        "/board/view/*",
                        "/board/gallery-write"
                )
//                 접근 불가 페이지 제외
//                 인터셉터 적용이 된 페이지를 제외한 모든 페이지 접근을 가능하게 하기 위한 주석처리
//                .excludePathPatterns("/**")
        ;
    }
}