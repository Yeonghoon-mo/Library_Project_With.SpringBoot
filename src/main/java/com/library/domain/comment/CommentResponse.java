package com.library.domain.comment;

import com.library.status.YnStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id; // 댓글 번호(PK)
    private Long boardId; // 게시글 번호(FK)
    private Long memberId; // 회원 번호(FK)
    private String memberName; // 작성자(회원) 이름
    private String memberProfileImage; // 작성자(회원) 프로필 사진
    private Long parentCommentId; // 부모 댓글 번호
    private Integer commentLevel; // 댓글 레벨 (0 = 댓글, 1 = 대댓글)
    private String content; // 내용
    private YnStatus deleteYn; // 삭제 여부
    private LocalDateTime createdDate; // 댓글 생성일

}
