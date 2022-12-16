package com.library.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardViewRequest {

    private Long boardId;     // 게시글 번호
    private Long memberId;    // 로그인한 회원 번호

}
