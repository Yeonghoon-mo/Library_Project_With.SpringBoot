package com.library.domain.menu.sub;

import com.library.domain.menu.main.MainMenu;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubMenuRequest {

    private MainMenu mainMenuId;          // 메뉴 번호(FK)
    private String url;                 // URL
    private String boardType;           // 게시글 유형 구분 ( 0 = 공지사항형, 1 = 썸네일형 )
    private String name;                // 상세 메뉴 이름
    private YnStatus deleteYn;          // 삭제 여부

    public SubMenu toEntity() {
        return SubMenu.builder()
                .mainMenuId(mainMenuId)
                .url(url)
                .boardType(boardType)
                .name(name)
                .deleteYn(deleteYn)
                .build();
    }
}
