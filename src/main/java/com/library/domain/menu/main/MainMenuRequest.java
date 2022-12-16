package com.library.domain.menu.main;

import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainMenuRequest {

    private String url;             // URL
    private String name;            // 메뉴 이름
    private YnStatus deleteYn;      // 삭제 여부

    public MainMenu toEntity() {
        return MainMenu.builder()
                .url(url)
                .name(name)
                .deleteYn(deleteYn)
                .build();
    }
}
