package com.library.domain.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberRepositoryCustom {

    // 관리자 페이지 회원 리스트 페이징
    Page<MemberResponse> findAll(MemberInquirySearchCondition condition, Pageable pageable);

    // 회원 상세페이지 Join
    Optional<MemberResponse> findByMemberId(Long id);

    // DB에 저장된 회원 객체 조회
    Member findByLoginId(String loginId);

    // 회원 아이디, 관리자 아이디 구분 ( 로그인에 사용 )
    Member findByMemberTypeLoginId(LoginRequest params);

}
