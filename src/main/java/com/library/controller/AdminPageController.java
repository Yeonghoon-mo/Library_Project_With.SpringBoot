package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager")
public class AdminPageController {

    // index Page
    @GetMapping
    public String adminIndex() {
        return "/admin/index";
    }

    // 게시판관리 - 게시글 리스트
    @GetMapping("board-manager-list")
    public String boardManager(@RequestParam final String boardType) {
        // boardType 이 0이면 기본, 1이면 썸네일형
        return (boardType.equals("0")) ? "/admin/board-manager-list" : "/admin/gallery-board-list";
    }

    // 게시판관리 - 공지사항 등록
    @GetMapping("board-manager-write")
    public String noticeWrite(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/board-manager-write";
    }

    // 게시판관리 - 게시글 상세
    @GetMapping("board-manager-view/{id}")
    public String noticeBoardView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/board-manager-view";
    }

    // 게시판관리 - 썸네일형 게시판 리스트
    @GetMapping("gallery-board-list")
    public String galleryBoardList() {
        return "/admin/gallery-board-list";
    }

    // 게시판관리 - 썸네일형 게시판 등록
    @GetMapping("gallery-board-write")
    public String galleryBoardWrite(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/gallery-board-write";
    }

    // 게시판관리 - 썸네일형 게시판 상세 페이지
    @GetMapping("gallery-board-view/{id}")
    public String galleryBoardView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/gallery-board-view";
    }

    // 기준정보관리 - 관리자 관리
    @GetMapping("manager")
    public String adminList(@RequestParam(required = false) final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/admin-manager";
    }

    // 회원관리 - 회원 리스트
    @GetMapping("member-list")
    public String memberList() {
        return "/admin/member-list";
    }

    // 회원관리 - 회원관리 상세 페이지
    @GetMapping("member-view/{id}")
    public String memberView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/member-view";
    }

    // 회원관리 - 정회원 승인 리스트
    @GetMapping("regular-member-authority")
    public String authorityList() {
        return "/admin/regular-member-authority";
    }

    // 회원관리 - 정회원 승인 리스트 상세페이지
    @GetMapping("regular-member-authority-view/{id}")
    public String authorityView(@PathVariable final Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/regular-member-authority-view";
    }
}
