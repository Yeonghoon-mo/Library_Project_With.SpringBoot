package com.library.domain.board;

import com.library.domain.attach.AttachResponse;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value="게시글 생성", notes="게시판에 게시글을 생성합니다.")
    @PostMapping
    public Long save(final BoardRequest params){
        return boardService.save(params);
    }

    // 게시글 수정
    @ApiOperation(value="게시글 수정", notes="게시글 ID를 이용하여 게시글을 수정합니다.")
    @PostMapping("{id}")
    public Long update(@PathVariable final Long id, final BoardRequest params){
        return boardService.update(id, params);
    }

    // 게시글 삭제
    @ApiOperation(value="게시글 삭제", notes="게시글 ID를 이용하여 게시글을 삭제합니다.")
    @DeleteMapping("{id}")
    public Long delete(@PathVariable final Long id){
        return boardService.delete(id);
    }

    // 게시글 상세정보 조회
    @ApiOperation(value="게시글 상세정보 조회", notes="게시글 ID를 이용하여 게시글의 상세정보를 확인합니다.")
    @GetMapping("{id}")
    public BoardResponse findById(@PathVariable final Long id){
        return boardService.findById(id);
    }

    // 게시글 리스트
    @ApiOperation(value="게시글 리스트 확인", notes="게시글 리스트를 확인합니다.")
    @GetMapping
    public Page<BoardResponse> findAll(final BoardInquirySearchCondition condition, final Pageable pageable){
        return boardService.findAll(condition, pageable);
    }

    // 게시글 인덱스 리스트
    @ApiOperation(value="게시글 메인 Page Index 리스트 확인", notes="게시글 메인 Page Index List를 확인합니다.")
    @GetMapping("/index/list")
    public List<BoardResponse> findByBoardIndexList(final BoardInquirySearchCondition condition) {
        return boardService.findByBoardIndexList(condition);
    }

    // 공지사항 리스트
    @ApiOperation(value="공지사항 리스트 확인", notes="게시글 리스트 중 공지사항 리스트만 확인합니다.")
    @GetMapping("/notice/list")
    public List<BoardResponse> findAllByNoticeYn(final String noticeYn) {
        return boardService.findAllByNoticeYn(noticeYn);
    }

    // 파일 리스트
    @ApiOperation(value="파일 리스트 확인", notes="업로드 된 파일 리스트 확인")
    @GetMapping("{id}/files")
    public List<AttachResponse> printFileList(@PathVariable final Long id) {
        return boardService.getAttachFileList(id);
    }

    // 게시글 상세정보 조회 (With. 좋아요)
    @ApiOperation(value="좋아요 & 싫어요 포함 게시글 상세정보 확인")
    @GetMapping("/{id}/like")
    public BoardViewResponse findBoardWithLike(@PathVariable final Long id) {
        return boardService.findBoardWithLike(id);
    }

}
