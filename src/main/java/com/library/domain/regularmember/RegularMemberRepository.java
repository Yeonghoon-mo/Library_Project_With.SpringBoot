package com.library.domain.regularmember;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegularMemberRepository extends JpaRepository<RegularMember, Long> {

    // 회원 테이블에서 연결된 FK키 ID값 조회
    Optional<RegularMember> findByMemberId(final Long memberId);

}
