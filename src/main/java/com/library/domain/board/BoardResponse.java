package com.library.domain.board;

import com.library.domain.member.MemberResponse;
import com.library.domain.menu.main.MainMenuResponse;
import com.library.domain.menu.sub.SubMenuResponse;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

    private Long id;                        // 게시글 번호(PK)
    private MemberResponse member;          // 회원 (FK)
    private SubMenuResponse boardMenuNum;   // 게시판 타입 (서브메뉴)
    private MainMenuResponse mainMenuNum;   // 게시판 타입 (메인메뉴)
    private String title;                   // 게시글 제목
    private String content;                 // 게시글 내용
    private String writer;                  // 게시글 작성자
    private int hits;                       // 조회수
    private String repImage;                // 게시글 대표이미지 SaveName
    private String saveNameImage;           // 대표이미지 Original Name
    private String boardKeyword;            // 게시글 키워드
    private String noticeYn;                // 공지사항 사용 여부
    private YnStatus deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;      // 게시글 생성일
    private LocalDateTime modifiedDate;     // 게시글 수정일

    public BoardResponse(Board entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.repImage = entity.getRepImage();
        this.saveNameImage = entity.getSaveNameImage();
        this.boardKeyword = entity.getBoardKeyword();
        this.noticeYn = entity.getNoticeYn();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

    public BoardResponse(Board board, MemberResponse member) {
        this(board);
        this.member = member;
    }

    public BoardResponse(Board board, SubMenuResponse subMenuResponse, MainMenuResponse mainMenuResponse, MemberResponse memberResponse) {
        this(board);
        this.boardMenuNum = subMenuResponse;
        this.mainMenuNum = mainMenuResponse;
        this.member = memberResponse;
    }
}
