package com.library.domain.menu.sub;

import com.library.domain.entity.BaseEntity;
import com.library.domain.menu.main.MainMenu;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_sub_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                     // 상세 메뉴 번호(PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_menu_id")
    private MainMenu mainMenuId;              // 메뉴 번호(FK)

    private String url;                     // URL
    private String boardType;               // 게시글 유형 구분 ( 0 = 공지사항형, 1 = 썸네일형 )
    private String name;                    // 상세 메뉴 이름
    private YnStatus deleteYn;              // 삭제 여부

    @Builder
    public SubMenu(String url, MainMenu mainMenuId, String boardType, String name, YnStatus deleteYn) {
        this.url = url;
        this.mainMenuId = mainMenuId;
        this.boardType = boardType;
        this.name = name;
        this.deleteYn = deleteYn;
    }

}
