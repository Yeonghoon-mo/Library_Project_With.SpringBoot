package com.library.domain.menu.main;

import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainMenuResponse {

    private Integer id;         // 메뉴 번호(PK)
    private String url;         // URL
    private String name;        // 메뉴 이름
    private YnStatus deleteYn;  // 삭제 여부

    public MainMenuResponse(MainMenu entity) {
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.name = entity.getName();
        this.deleteYn = entity.getDeleteYn();
    }
}
