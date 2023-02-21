package com.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class UserJoinPageController {

    // 회원가입 약관동의 Page
    @GetMapping("agree")
    public String joinAgree() {
        return "/user/join/joinAgree";
    }

    // 회원 타입 Select Page
    @GetMapping("member-type")
    public String memberType() {
        return "/user/join/joinMemberType";
    }

    // 일반회원 가입 Page
    @GetMapping("general-create")
    public String generalMemberCreate() {
        return "/user/join/joinGeneralCreate";
    }

    // 정회원 가입 Page
    @GetMapping("regular-create")
    public String regularMemberCreate() {
        return "/user/join/joinRegularCreate";
    }

    // 회원가입 완료 Page
    @GetMapping("confirm")
    public String joinConfirm() {
        return "/user/join/joinConfirm";
    }

    // 정회원 회원가입 완료 Page
    @GetMapping("regular-confirm")
    public String regularJoinConfirm() {
        return "/user/join/regularJoinConfirm";
    }
}