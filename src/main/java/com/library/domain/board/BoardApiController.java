package com.library.domain.board;

import com.library.domain.attach.AttachResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    // 게시글 생성
    @PostMapping
    public Long save(final BoardRequest params){
        return boardService.save(params);
    }

    // 게시글 수정
    @PostMapping("{id}")
    public Long update(@PathVariable final Long id, final BoardRequest params){
        return boardService.update(id, params);
    }

    // 게시글 삭제
    @DeleteMapping("{id}")
    public Long delete(@PathVariable final Long id){
        return boardService.delete(id);
    }

    // 게시글 상세정보 조회
    @GetMapping("{id}")
    public BoardResponse findById(@PathVariable final Long id){
        return boardService.findById(id);
    }

    // 게시글 리스트
    @GetMapping
    public Page<BoardResponse> findAll(final BoardInquirySearchCondition condition, final Pageable pageable){
        return boardService.findAll(condition, pageable);
    }

    // 게시글 인덱스 리스트
    @GetMapping("/index/list")
    public List<BoardResponse> findByBoardIndexList(final BoardInquirySearchCondition condition) {
        return boardService.findByBoardIndexList(condition);
    }

    // 공지사항 리스트
    @GetMapping("/notice/list")
    public List<BoardResponse> findAllByNoticeYn(final String noticeYn) {
        return boardService.findAllByNoticeYn(noticeYn);
    }

    // 파일 리스트
    @GetMapping("{id}/files")
    public List<AttachResponse> printFileList(@PathVariable final Long id) {
        return boardService.getAttachFileList(id);
    }

    // 게시글 상세정보 조회 (With. 좋아요)
    @GetMapping("/{id}/like")
    public BoardViewResponse findBoardWithLike(@PathVariable final Long id) {
        return boardService.findBoardWithLike(id);
    }

}
