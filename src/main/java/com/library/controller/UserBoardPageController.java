package com.library.controller;

import com.library.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class UserBoardPageController {

    private final BoardService boardService;

    // 공지사항형 게시판 리스트
    @GetMapping("list")
    public String boardLists() {
        return "/user/board/boardList";
    }

    // 공지사항형 게시글 작성 Page
    @GetMapping("write")
    public String boardWrite(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "/user/board/boardWrite";
    }

    // 공지사항형 게시글 View
    @GetMapping("view/{id}")
    public String boardView(@PathVariable final Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        cookieProcessing(id, request, response);
        model.addAttribute("id", id);
        return "/user/board/boardView";
    }

    // 썸네일형 게시판 게시글 리스트
    @GetMapping("gallery-list")
    public String galleryLists() {
        return "/user/board/galleryBoardList";
    }

    // 정회원 소개 List
    @GetMapping("regular-list")
    public String regularIntroList() {
        return "/user/board/regularIntroList";
    }

    // 정회원 상세 페이지
    @GetMapping("regular-view/{id}")
    public String regularIntroView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "/user/board/regularIntroView";
    }

    // 게시글 검색 Page
    @GetMapping("search")
    public String searchBoards() {
        return "/user/board/searchBoard";
    }

    // 쿠키 저장 Method ( 게시글 조회수 증가 중복체크 사용 )
    private void cookieProcessing(Long id ,HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie c : cookies) {
                String name = "cookie-board-" + id;
                if (StringUtils.equals(name, c.getName())) {
                    cookie = c;
                    break;
                }
            }

            if (cookie == null) {
                cookie = new Cookie("cookie-board-" + id, String.valueOf(id));
                cookie.setMaxAge(600); // 쿠키 저장 시간 ( 초 기준 )
                response.addCookie(cookie);
                boardService.increaseHits(id); // 조회수 증가
            }
        }
    }
}
