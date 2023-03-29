package com.library.domain.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    /**
     * 회원가입 메일 보내기
     */
    @Async
    public void sendJoinMail(String email, String authToken) throws MessagingException {
        SimpleMailMessage smm = new SimpleMailMessage();
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmHelper = new MimeMessageHelper(mm, true, "UTF-8");

        String htmlStr = "안녕하세요! " + email + " 님의 인증번호는" + authToken + " 입니다.";

        mmHelper.setTo(email);
        mmHelper.setSubject("Library Project 회원가입 인증번호");
        mmHelper.setText(htmlStr, true);
        javaMailSender.send(mm);
    }

    /**
     * 비밀번호 찾기 메일 보내기
     */
    @Async
    public void sendPasswordFindMail(String email, String authToken) throws MessagingException {
        SimpleMailMessage smm = new SimpleMailMessage();
        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmHelper = new MimeMessageHelper(mm, true, "UTF-8");

        String htmlStr = "안녕하세요! " + email + " 님의 비밀번호 찾기 인증번호는" + authToken + " 입니다.";

        mmHelper.setTo(email);
        mmHelper.setSubject("Library Project 비밀번호 찾기 인증번호");
        mmHelper.setText(htmlStr, true);
        javaMailSender.send(mm);
    }
}
