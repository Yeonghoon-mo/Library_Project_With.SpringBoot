package com.library.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public Long saveComment(@RequestBody final CommentRequest params) {
        return commentService.saveComment(params);
    }

    // 댓글 수정
    @PatchMapping("{id}")
    public Long updateComment(@PathVariable final Long id, @RequestBody final CommentRequest params) {
        return commentService.updateComment(id, params);
    }

    // 댓글 삭제
    @DeleteMapping("{id}")
    public Long deleteComment(@PathVariable final Long id) {
        return commentService.deleteComment(id);
    }

    // 관리자 댓글 삭제
    @DeleteMapping("{id}/admin")
    public Long deleteAdminComment(@PathVariable final Long id) {
        return commentService.deleteAdminComment(id);
    }

    // 댓글 리스트 조회 (부모 or 자식)
    @GetMapping
    public List<CommentListResponse> findAllComments(final CommentSearch params) {
        return commentService.findAllComments(params);
    }

}
