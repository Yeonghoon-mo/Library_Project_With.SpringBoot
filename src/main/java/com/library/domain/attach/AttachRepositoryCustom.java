package com.library.domain.attach;

import java.util.List;

public interface AttachRepositoryCustom {

    // 게시판 파일 삭제처리
    void deleteAttach(Long boardId);

    // 게시판 파일 삭제
    void deleteAllByAttachIds(List<Long> attachIds);

    // 파일 카운트
    Long selectAttachTotalCount(Long id);

    // 파일 목록 조회
    List<Attach> findByAttachList(Long id);

}
