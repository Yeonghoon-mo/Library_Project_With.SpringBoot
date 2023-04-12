package com.library.domain.member;

import com.library.status.YnStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInquirySearchCondition {

    // Member 검색 조건 멤버변수
    private String searchType;      // 검색조건
    private String keyword;         // 검색 키워드
    private MemberType memberType;      // 회원 유형
    private String authorityYn;     // 정회원 회원가입 승인여부
    private YnStatus deleteYn;      // 삭제 여부

}
