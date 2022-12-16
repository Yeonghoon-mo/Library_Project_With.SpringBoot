package com.library.domain.board;

import com.library.status.YnStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardViewResponse {

    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String writer;
    private int hits;
    private String repImage;
    private String saveNameImage;
    private String boardKeyword;
    private String noticeYn;
    private YnStatus deleteYn;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String loginId;
    private String profileImage;
    private int likeCnt;
    private int badCnt;
    private Integer likeType;

}
