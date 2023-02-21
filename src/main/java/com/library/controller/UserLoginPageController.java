package com.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserLoginPageController {

    // 로그인 Page
    @GetMapping("")
    public String loginPage() {
        return "/user/login/login";
    }

    // 아이디 찾기 Page
    @GetMapping("search-id")
    public String searchId() {
        return "/user/login/searchId";
    }

    // 아이디 찾기 완료 Page
    @GetMapping("search-id-info")
    public String searchIdInfo() {
        return "/user/login/searchIdInfo";
    }

    // 비밀번호 찾기 Page
    @GetMapping("search-password")
    public String searchPassword() {
        return "/user/login/searchPassword";
    }

    // 비밀번호 찾기 완료 Page
    @GetMapping("search-password-info")
    public String searchPasswordInfo() {
        return "/user/login/searchPasswordInfo";
    }
}
