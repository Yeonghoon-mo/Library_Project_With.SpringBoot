package com.library.interceptor;

import com.library.domain.member.Member;
import com.library.domain.member.MemberType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginCheckInterceptorMember implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Member memberLogin = (Member) session.getAttribute("loginMember"); // 로그인 정보를 가져옴

        // 비회원인 경우, 인덱스 페이지로 리다이렉트
        if (memberLogin == null) {
            if (!StringUtils.contains(request.getRequestURI(), "view")) {
                response.sendRedirect("/login");
                return false;
            }
        }

        // 정회원 게시판 글쓰기의 경우
        if (StringUtils.contains(request.getQueryString(), "boardMenuNum=3")) {
            // 비회원인 경우
            if (memberLogin == null) {
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print("<script>alert('정회원 게시판은 로그인 후 조회가 가능합니다.'); history.back();</script>");
                out.flush();
                out.close();
                response.sendRedirect("/login");
                return false;
            } else {
                // 일반회원인 경우
                MemberType type = memberLogin.getType();
                if (type == MemberType.GENERAL) {
                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('정회원 게시판은 정회원만 조회가 가능합니다.'); history.back();</script>");
                    out.flush();
                    out.close();
                    return false;
                }
            }
        }

        return true;
    }
}
