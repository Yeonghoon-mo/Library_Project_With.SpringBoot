package com.library.domain.mail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailResponse {

    private String mailType;               // 이메일 전송 타입
    private String email;                  // 가입 Email
    private String authToken;              // UUID
    private LocalDateTime expireDate;      // 만료 시간

    // RequestParam 으로 값을 받기 위한 생성자.
    public MailResponse(String mailType, String email, String authToken, LocalDateTime expireDate) {
        this.mailType = mailType;
        this.email = email;
        this.authToken = authToken;
        this.expireDate = expireDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

}
