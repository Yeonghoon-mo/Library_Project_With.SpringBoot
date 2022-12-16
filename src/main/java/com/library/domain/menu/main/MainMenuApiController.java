package com.library.domain.menu.main;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main-menu")
public class MainMenuApiController {

    private final MainMenuService mainMenuService;

    // 전체 메인 메뉴 리스트 조회
    @GetMapping
    public List<MainMenuResponse> findAll() {
        return mainMenuService.findAll();
    }

}
