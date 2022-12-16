package com.library.domain.board;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    // 게시글 상세정보 조회 (with. 좋아요)
    BoardViewResponse findByBoardId(BoardViewRequest params);

}
