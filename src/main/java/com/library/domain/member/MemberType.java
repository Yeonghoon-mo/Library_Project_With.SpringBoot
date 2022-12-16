package com.library.domain.member;

import lombok.Getter;

@Getter
public enum MemberType {
    GENERAL("일반회원"), REGULAR("정회원"), ADMIN("관리자");

    private final String explain;

    MemberType(String explain){
        this.explain = explain;
    }

}
