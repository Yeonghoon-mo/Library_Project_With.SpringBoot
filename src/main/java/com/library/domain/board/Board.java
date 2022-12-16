package com.library.domain.board;

import com.library.domain.attach.FileResponse;
import com.library.domain.entity.BaseEntity;
import com.library.domain.like.Like;
import com.library.domain.member.Member;
import com.library.domain.menu.sub.SubMenu;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        // 게시글 번호(PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;                  // 회원번호(FK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_menu_num")
    private SubMenu boardMenuNum;           // 게시판 타입

    private String title;                   // 게시글 제목
    private String content;                 // 게시글 내용
    private String writer;                  // 게시글 작성자
    private int hits;                       // 조회수
    private String repImage;                // 게시글 대표이미지 SaveName
    private String saveNameImage;           // 대표이미지 Original Name
    private String boardKeyword;            // 게시글 키워드
    private String noticeYn;                // 공지사항 사용 여부

    @Enumerated(EnumType.STRING)
    private YnStatus deleteYn;              // 삭제 여부

    @Builder
    public Board(Member member, SubMenu boardMenuNum, String title, String content, String writer, int hits, String repImage, String saveNameImage, String boardKeyword,
                 String noticeYn, YnStatus deleteYn) {
        this.member = member;
        this.boardMenuNum = boardMenuNum;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.hits = hits;
        this.repImage = repImage;
        this.saveNameImage = saveNameImage;
        this.boardKeyword = boardKeyword;
        this.noticeYn = noticeYn;
        this.deleteYn = deleteYn;
    }

    // 게시글 수정
    public void update(BoardRequest params, FileResponse repImage){
        this.title = params.getTitle();
        this.content = params.getContent();
        this.repImage = (repImage == null) ? this.repImage : repImage.getOriginalFilename();
        this.saveNameImage = (repImage == null) ? this.saveNameImage : repImage.getSaveFilename();
        this.boardKeyword = params.getBoardKeyword();
        this.noticeYn = params.getNoticeYn();
    }

    // 게시글 삭제
    public void delete(){
        this.deleteYn = YnStatus.Y;
        this.noticeYn = "N";
    }

    // 조회수 증가
    public void increaseHits(){
        this.hits++;
    }

}
