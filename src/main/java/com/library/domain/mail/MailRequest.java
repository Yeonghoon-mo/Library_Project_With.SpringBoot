package com.library.domain.mail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailRequest {

    private String mailType;            // 이메일 전송 타입
    private String email;               // 인증번호를 받을 email
    private String authToken;           // 8자리 토큰
    private String address;             // 메일 주소
    private String title;               // 제목
    private String message;             // 메세지 내용

    public Mail toEntity(String authToken) {
        return Mail.builder()
                .email(email)
                .authToken(authToken)
                .mailType(mailType)
                .expired(false)
                .build();
    }

}
