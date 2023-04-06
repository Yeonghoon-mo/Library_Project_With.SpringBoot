package com.library.utils;

import com.library.domain.member.Member;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class MemberUtil {

    // 회원 & 관리자 로그인 세션값 구별하는 static Method
    public static Member getLoginSessionMember() {
        Member user = getSessionMember();
        Member admin = getSessionAdmin();

        if (user == null && admin == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        return (user == null) ? admin : user;
    }

    // 회원 세션값 넣기
    public static Member getSessionMember() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = sra.getRequest().getSession();
        return (Member) session.getAttribute("loginMember");
    }

    // 관리자 세션값 넣기
    public static Member getSessionAdmin() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = sra.getRequest().getSession();
        return (Member) session.getAttribute("login");
    }

    // Null Check
    public static Member getSessionMember(boolean allowNull) {
        if (!allowNull) {
            if (getSessionMember() == null) {
                throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
            }
        }
        return getSessionMember();
    }

    // 세션 값이 저장되어 있는 Member ID Response 메서드
    public static Long getSessionMemberId() {
        Member member = getSessionMember();
        return (member == null) ? null : member.getId();
    }

}
