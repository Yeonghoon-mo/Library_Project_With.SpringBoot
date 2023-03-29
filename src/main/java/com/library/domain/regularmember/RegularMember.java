package com.library.domain.regularmember;

import com.library.domain.member.Member;
import com.library.domain.member.MemberRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_regular_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegularMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String phone;                   // 연락처

    @Builder
    public RegularMember(Member member, String phone) {
        this.member = member;
        this.phone = phone;
    }

    // 정회원 수정
    public void update(MemberRequest params) {
        this.phone = params.getPhone();
    }

}
