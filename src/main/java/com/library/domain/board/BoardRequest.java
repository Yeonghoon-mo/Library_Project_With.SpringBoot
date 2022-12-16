package com.library.domain.board;

import com.library.domain.attach.FileResponse;
import com.library.domain.member.Member;
import com.library.domain.menu.sub.SubMenu;
import com.library.status.YnStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardRequest {

    private Long memberId;                                         // 회원 번호
    private SubMenu boardMenuNum;                                  // 게시판별 게시글 카테고리 분류
    private String title;                                          // 게시글 제목
    private String content;                                        // 게시글 내용
    private String writer;                                         // 게시글 작성자
    private String repImage;                                       // 게시글 대표이미지 SaveName
    private String saveNameImage;                                  // 대표이미지 Original Name`
    private String boardKeyword;                                   // 게시글 키워드
    private String noticeYn;                                       // 공지사항 사용 여부
    private YnStatus deleteYn;                                     // 게시글 작성자

    // Entity Class 컬럼에 등록되어 있는 데이터를 받는 것이 아닌 직접 선언한 멤버변수
    private String changeYn;                                       // 파일 변경 여부
    private List<Long> removeFileIds = new ArrayList<>();          // 삭제할 파일 인덱스 리스트
    private MultipartFile thumbnail;                               // 대표 이미지
    private List<MultipartFile> attachFiles = new ArrayList<>();   // 첨부파일 리스트

    public Board toEntity(Member member, FileResponse repImage){
        return Board.builder()
                .member(member)
                .boardMenuNum(boardMenuNum)
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .repImage((repImage == null) ? "" : repImage.getOriginalFilename())
                .saveNameImage((repImage == null) ? "" : repImage.getSaveFilename())
                .boardKeyword(boardKeyword)
                .noticeYn(noticeYn)
                .deleteYn(deleteYn)
                .build();
    }

}
