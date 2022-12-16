package com.library.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    private String loginId;           // LoginId
    private String password;          // PASSWORD
    private MemberType memberType;    // 회원 타입
    private String authorityYn;       // 정회원 회원가입 인증 여부

}
