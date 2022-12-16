package com.library.domain.board;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    // 공지사항 리스트 조회
    List<Board> findAllByNoticeYn(final String noticeYn, final Sort sort);

}
