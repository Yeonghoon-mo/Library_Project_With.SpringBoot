package com.library.domain.menu.sub;

import com.library.domain.menu.main.MainMenuResponse;
import com.library.status.YnStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubMenuResponse {

    private Integer id;                     // 상세 메뉴 번호(PK)
    private MainMenuResponse mainMenuId;    // 메뉴 번호(FK)
    private String url;                     // URL
    private String boardType;               // 게시글 유형 구분 ( 0 = 공지사항형, 1 = 썸네일형 )
    private String name;                    // 상세 메뉴 이름
    private YnStatus deleteYn;              // 삭제 여부

    public SubMenuResponse(SubMenu entity) {
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.boardType = entity.getBoardType();
        this.name = entity.getName();
        this.deleteYn = entity.getDeleteYn();
    }

    public SubMenuResponse(SubMenu subMenu, MainMenuResponse mainMenuResponse) {
        this(subMenu);
        this.mainMenuId = mainMenuResponse;
    }
}
