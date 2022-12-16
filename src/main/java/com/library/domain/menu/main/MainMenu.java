package com.library.domain.menu.main;

import com.library.domain.entity.BaseEntity;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_main_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainMenu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;             // 메뉴 번호(PK)

    private String url;             // URL
    private String name;            // 메뉴 이름
    private YnStatus deleteYn;      // 삭제 여부

    @Builder
    public MainMenu(String url, String name, YnStatus deleteYn) {
        this.url = url;
        this.name = name;
        this.deleteYn = deleteYn;
    }
}
