package com.library.domain.comment;

import com.library.domain.board.Board;
import com.library.domain.member.Member;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

    private Long boardId; // 게시글 번호 (FK)
    private Long parentCommentId; // 부모 댓글 번호
    private Integer commentLevel; // 댓글 레벨 (0 = 댓글, 1 = 대댓글)
    private String content; // 내용

    public Comment toEntity(Board board, Member member){
        return Comment.builder()
                .board(board)
                .member(member)
                .parentCommentId(parentCommentId)
                .commentLevel(commentLevel)
                .content(content)
                .deleteYn(YnStatus.N)
                .build();
    }
}
