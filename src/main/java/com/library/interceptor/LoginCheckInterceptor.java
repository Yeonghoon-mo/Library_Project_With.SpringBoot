package com.library.interceptor;

import com.library.domain.member.Member;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        Member login = (Member) session.getAttribute("login"); // 로그인 정보를 가져옴

        // 로그인이 안되어있으면 ?
        if (login == null) {
            response.sendRedirect("/manager");
            return false;
        }
        return true;
    }
}
