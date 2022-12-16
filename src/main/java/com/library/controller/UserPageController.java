package com.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    // Index Page
    @GetMapping()
    public String userIndex() {
        return "/user/index";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage() {
        return "/user/mypage";
    }

    // Contact Page
    @GetMapping("contact")
    public String contact() {
        return "/user/contact";
    }

    // SiteMap Page
    @GetMapping("sitemap")
    public String siteMap() {
        return "/user/sitemap";
    }
}
