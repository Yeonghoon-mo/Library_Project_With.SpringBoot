package com.library.config;

import com.library.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class LoginConfig implements WebMvcConfigurer {

    // 인터셉터 로그인 ( ADMIN, MEMBER )
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                // 접근 불가 Page 추가
                .addPathPatterns("/manager/**")
                // 접근 불가 페이지 제외
                .excludePathPatterns("/manager","/api/members/login", "/admin/**");

    }
}
