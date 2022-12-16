package com.library.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {

    private Long id;                // PK
    private String loginId;         // login Id
    private String name;            // 회원 이름

    public LoginResponse(Member entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
    }
}
