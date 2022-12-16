package com.library.domain.comment;

import com.library.domain.board.Board;
import com.library.domain.entity.BaseEntity;
import com.library.domain.member.Member;
import com.library.status.YnStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 번호(PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board; // 게시글 번호(FK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원 번호(FK)
    private Long parentCommentId; // 부모 댓글 번호
    private Integer commentLevel; // 댓글 레벨 ( 0 = 댓글, 1 = 대댓글 )
    private String content; // 내용
    private YnStatus deleteYn; // 삭제 여부

    @Builder
    public Comment(Board board, Member member, Long parentCommentId, Integer commentLevel, String content, YnStatus deleteYn){
        this.board = board;
        this.member = member;
        this.parentCommentId = parentCommentId;
        this.commentLevel = commentLevel;
        this.content = content;
        this.deleteYn = deleteYn;
    }

    // 댓글 수정
    public void update(CommentRequest params) {
        this.content = params.getContent();
    }

    // 댓글 삭제
    public void delete() {
        this.deleteYn = YnStatus.Y;
    }
}
