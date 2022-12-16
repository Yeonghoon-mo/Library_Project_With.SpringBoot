package com.library.domain.mail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tb_mail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mail {

    private static final Long MAX_EXPIRE_TIME = 5L; // 5분

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 메일 일련번호(PK)

    private String mailType;            // 메일 전송 타입 ( 회원가입 or 비밀번호 찾기 )
    private String email;               // 가입 Email
    private String authToken;           // UUID
    private Boolean expired;            // 만료 여부
    private LocalDateTime expireDate;   // 만료 시간

    @Builder
    public Mail(String mailType, String email, String authToken, Boolean expired) {
        this.mailType = mailType;
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }

    public void useToken() {
        this.expired = true;
    }


}
