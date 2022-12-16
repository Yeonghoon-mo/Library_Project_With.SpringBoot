package com.library.domain.like;

import lombok.Getter;

@Getter
public class LikeResponse {

    private Long id; // 좋아요 번호(PK)
    private Long boardId; // 게시글 번호(FK)
    private Long memberId; // 회원 번호(FK)
    private long likeType; // 좋아요, 싫어요 구분

}
