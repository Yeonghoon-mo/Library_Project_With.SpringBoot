package com.library.domain.like;

import com.library.domain.board.BoardViewRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    // 좋아요, 싫어요 수 조회
    LikeCountResponse countByBoardId(BoardViewRequest params);

}
