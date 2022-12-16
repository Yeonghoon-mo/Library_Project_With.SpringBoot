package com.library.domain.menu.sub;

import com.library.domain.menu.main.MainMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubMenuService {

    private final SubMenuRepository subMenuRepository;

    // 서브메뉴 리스트 조회
    public List<SubMenuResponse> findAll() {
        List<SubMenu> list = subMenuRepository.findAll();
//            return list.stream().map(SubMenuResponse::new).collect(Collectors.toList());
        return list.stream().map(subMenu -> {
            MainMenuResponse mainMenuResponse = new MainMenuResponse(subMenu.getMainMenuId());
            return new SubMenuResponse(subMenu, mainMenuResponse);
        }).collect(Collectors.toList());

    }

}
