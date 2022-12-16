package com.library.domain.member;

import com.library.domain.attach.FileResponse;
import com.library.domain.mail.Mail;
import com.library.domain.mail.MailRepository;
import com.library.domain.mail.MailRequest;
import com.library.domain.mail.MailService;
import com.library.domain.regularmember.RegularMember;
import com.library.domain.regularmember.RegularMemberRepository;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import com.library.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegularMemberRepository regularMemberRepository;
    private final MailRepository mailRepository;
    private final MailService mailService;
    private final FileUtil fileUtil;
    private final PasswordEncoder encoder;

    /** 관리자 생성 */
    @Transactional
    public Long saveAdmin(final MemberRequest params) {
        params.encodingPassword(encoder.encode(params.getPassword()));
        Member member = memberRepository.save(params.toEntity());
        return member.getId();
    }

    /** 관리자 수정 */
    @Transactional
    public Long updateAdmin(final Long id, final MemberRequest params) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        params.encodingPassword((StringUtils.isBlank(params.getPassword())) ? member.getPassword() : encoder.encode(params.getPassword()));
        member.update(params);
        return member.getId();
    }

    /** 회원가입 (일반회원, 정회원) */
    @Transactional
    public Long saveMember(final MemberRequest params) {

        params.encodingPassword(encoder.encode(params.getPassword()));
        FileResponse profileImage = fileUtil.uploadFile(params.getThumbnail()); // 대표 이미지 업로드
        Member member = memberRepository.save(params.toEntity(profileImage)); // 회원 생성

        if (params.getType() == MemberType.REGULAR) {
            regularMemberRepository.save(params.toEntity(member));
        }

        return member.getId();
    }

    /** 회원 수정 */
    @Transactional
    public Long updateMember(final Long id, final MemberRequest params){

        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)); // 회원
        FileResponse profileImage = fileUtil.uploadFile(params.getThumbnail()); // 대표 이미지 업로드
        member.updateProfileImage(profileImage); // 대표 이미지 수정

        // 정회원 정보 수정
        if(params.getType() == MemberType.REGULAR) {
            RegularMember regularMember = regularMemberRepository.findByMemberId(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
            regularMember.update(params);
        }

        return member.getId();
    }

    /** 회원 삭제 */
    @Transactional
    public Long deleteMember(final Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        member.delete();
        return id;
    }

    /** 회원 상세조회 */
    @Transactional
    public MemberResponse findByMember(final Long id){
        return memberRepository.findByMemberId(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    /** 회원 상세조회 ( 비밀번호 찾기 ) */
    @Transactional
    public LoginResponse findByMemberName(final String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        return new LoginResponse(member);
    }

    /** ID 값으로 비밀번호 변경 */
    @Transactional
    public Long passwordUpdate(final String loginId, final String password) {
        Member member = memberRepository.findByLoginId(loginId);
        member.updatePassword(encoder.encode(password));
        return member.getId();
    }

    /** 리스트 조회 */
    public Page<MemberResponse> findAll(final MemberInquirySearchCondition condition, final Pageable pageable){
        return memberRepository.findAll(condition, pageable);
    }

    /** 아이디 중복체크 */
    public boolean checkLoginIdDuplicate(final String id) {
        return memberRepository.existsByLoginId(id);
    }

    /** 회원가입 인증메일 발송 */
    @Transactional
    public Long createCheckNumber(final MailRequest params) throws MessagingException {
        String authToken = authToken();
        Mail mail = mailRepository.save(params.toEntity(authToken));
        if(params.getMailType().equals("0")) {
            mailService.sendJoinMail(mail.getEmail(), mail.getAuthToken());
        } else {
            mailService.sendPasswordFindMail(mail.getEmail(), mail.getAuthToken());
        }

        return mail.getId();
    }

    /** 이메일 인증번호 확인 */
    @Transactional
    public Long confirmEmail(final MailRequest params) {
        Mail mail = mailRepository.findValidAuthByEmail(params).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        mail.useToken();
        return mail.getId();
    }

    /** 정회원 회원가입 승인 */
    @Transactional
    public Long authorityApprove(final Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        member.regularMemberAuthorityYn();
        return id;
    }

    /** 로그인 처리 */
    public Long loginAction(final LoginRequest params, HttpSession session) {

        Member member = memberRepository.findByMemberTypeLoginId(params);
        if ( encoder.matches(params.getPassword(), member.getPassword()) == false ) {
            throw new RuntimeException("아이디 또는 비밀번호를 확인해 주세요.");
        }

        String sessionKey = (member.getType() == MemberType.ADMIN) ? "login" : "loginMember";
        session.setAttribute(sessionKey, member);
        return member.getId();
    }

    /** 로그아웃 처리 */
    public void logoutAction(HttpSession session) {
        session.invalidate();
    }

    /** 6자리 이메일 인증번호 난수 생성 Method */
    public static String authToken() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (2) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

}
