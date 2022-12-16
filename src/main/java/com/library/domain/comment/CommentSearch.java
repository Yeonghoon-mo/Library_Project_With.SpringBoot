package com.library.domain.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearch {

    private Long boardId; // 게시글 번호(FK)
    private Long memberId; // 회원 번호(FK)
    private Long parentCommentId; // 부모 댓글 번호
    private Integer commentLevel; // 댓글 레벨 (0 = 댓글, 1 = 대댓글)

}
