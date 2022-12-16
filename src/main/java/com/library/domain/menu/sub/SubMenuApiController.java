package com.library.domain.menu.sub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub-menu")
public class SubMenuApiController {

    private final SubMenuService subMenuService;

    // 전체 서브 메뉴 리스트 조회
    @GetMapping
    public List<SubMenuResponse> findAll() {
        return subMenuService.findAll();
    }
}
