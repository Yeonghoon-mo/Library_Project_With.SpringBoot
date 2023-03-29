package com.library.domain.member;

import com.library.domain.mail.MailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final MemberService memberService;

    // 관리자 생성
    @PostMapping("/admin")
    public Long saveAdmin(final MemberRequest params) {
        return memberService.saveAdmin(params);
    }

    // 관리자 수정
    @PostMapping("/admin/{id}")
    public Long updateAdmin(@PathVariable Long id, final MemberRequest params) {
        return memberService.updateAdmin(id, params);
    }

    // 회원 생성
    @PostMapping
    public Long saveMember(final MemberRequest params) {
        return memberService.saveMember(params);
    }

    // 회원 수정
    @PostMapping("/{id}")
    public Long updateMember(@PathVariable final Long id, final MemberRequest params) {
        return memberService.updateMember(id, params);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public Long deleteMember(@PathVariable final Long id) {
        return memberService.deleteMember(id);
    }

    // 회원 상세정보 조회
    @GetMapping("/{id}")
    public MemberResponse findById(@PathVariable final Long id) {
        return memberService.findByMember(id);
    }

    // 회원 상세조회 ( 비밀번호 찾기 )
    @GetMapping("/findPassword/{loginId}")
    public LoginResponse findByMemberName(@PathVariable final String loginId) {
        return memberService.findByMemberName(loginId);
    }

    // ID 값으로 비밀번호 변경
    @PostMapping("/password-update/{loginId}")
    public Long passwordUpdate(@PathVariable final String loginId, @RequestBody final String password) {
        return memberService.passwordUpdate(loginId, password);
    }

    // 리스트 조회
    @GetMapping
    public Page<MemberResponse> findAll(final MemberInquirySearchCondition condition, final Pageable pageable) {
        return memberService.findAll(condition, pageable);
    }

    // 아이디 중복 확인
    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> findByCheckId(@PathVariable final String id) {
        return ResponseEntity.ok(memberService.checkLoginIdDuplicate(id));
    }

    // 회원가입 인증메일 발송 (Front - Button)
    @PostMapping("/authentication-number")
    public Long createCheckNumber(@RequestBody final MailRequest params) throws MessagingException {
        return memberService.createCheckNumber(params);
    }

    // 이메일 인증 진행
    @GetMapping("/confirm-email")
    public Long confirmEmail(final MailRequest params) {
        return memberService.confirmEmail(params);
    }

    // 로그인
    @PostMapping("/login")
    public Long login(@RequestBody final LoginRequest params, HttpSession session) {
        return memberService.loginAction(params, session);
    }

    // 로그아웃
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        memberService.logoutAction(session);
    }

    // 정회원 회원가입 승인
    @PostMapping("/authority/{id}")
    public Long authorityApprove(@PathVariable final Long id) {
        return memberService.authorityApprove(id);
    }

}
