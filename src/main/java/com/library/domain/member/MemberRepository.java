package com.library.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    // 아이디 중복체크 확인
    boolean existsByLoginId(String loginId);

}
