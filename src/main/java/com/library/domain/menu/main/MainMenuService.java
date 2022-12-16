package com.library.domain.menu.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainMenuService {

    private final MainMenuRepository mainMenuRepository;

    // 메인메뉴 리스트 조회
    @Transactional
    public List<MainMenuResponse> findAll() {
        List<MainMenu> list = mainMenuRepository.findAll();
        return list.stream().map(MainMenuResponse::new).collect(Collectors.toList());
    }

}
