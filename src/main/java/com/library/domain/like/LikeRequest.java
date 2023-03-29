package com.library.domain.like;

import com.library.domain.board.Board;
import com.library.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRequest {

    private Long boardId; // 게시글 번호(FK)
    private int likeType; // 좋아요, 싫어요 구분

    public Like toEntity(Board board, Member member) {
        return Like.builder()
                .board(board)
                .member(member)
                .likeType(likeType)
                .build();
    }
}
