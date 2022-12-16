package com.library.domain.attach;

import com.library.domain.board.Board;
import com.library.domain.entity.BaseEntity;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_attach")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attach extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 파일 번호(PK)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // 게시글 번호(FK)

    private String originalName; // 원본 파일명
    private String saveName; // 저장 파일명
    private long size; // 파일 사이즈
    private YnStatus deleteYn; // 삭제 여부

    @Builder
    public Attach(Board board, String originalName, String saveName, long size){
        this.board = board;
        this.originalName = originalName;
        this.saveName = saveName;
        this.size = size;
        this.deleteYn = YnStatus.N;
    }

}
