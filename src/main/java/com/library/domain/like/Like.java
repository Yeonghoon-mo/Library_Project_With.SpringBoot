package com.library.domain.like;

import com.library.domain.board.Board;
import com.library.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 좋아요 일련번호(PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // 게시글 번호(FK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원 번호(FK)

    private long likeType; // 좋아요, 싫어요 구분

    @Builder
    public Like(Board board, Member member, long likeType){
        this.board = board;
        this.member = member;
        this.likeType = likeType;
    }

    // 좋아요, 싫어요 상태 변경
    public void changeLikeType(long likeType) {
        this.likeType = likeType;
    }

}
