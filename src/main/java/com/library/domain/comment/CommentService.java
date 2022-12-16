package com.library.domain.comment;

import com.library.domain.board.Board;
import com.library.domain.board.BoardRepository;
import com.library.domain.member.Member;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import com.library.utils.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @Transactional
    public Long saveComment(final CommentRequest params) {
        Board board = boardRepository.findById(params.getBoardId()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        Member member = MemberUtil.getSessionMember(false);
        return commentRepository.save(params.toEntity(board, member)).getId();
    }

    // 댓글 수정
    @Transactional
    public Long updateComment(@PathVariable final Long id, final CommentRequest params) {
        Comment entity = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        Member member = MemberUtil.getSessionMember(false);
        if (entity.getMember().getId().longValue() != member.getId().longValue()) {
            throw new CustomException(ErrorCode.IS_NOT_CREATOR_COMMENT);
        }
        entity.update(params);
        return entity.getId();
    }

    // 댓글 삭제
    @Transactional
    public Long deleteComment(@PathVariable final Long id) {
        Comment entity = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        Member member = MemberUtil.getSessionMember(false);
        if (entity.getMember().getId().longValue() != member.getId().longValue()) {
            throw new CustomException(ErrorCode.IS_NOT_CREATOR_COMMENT);
        }
        entity.delete();
        return id;
    }

    // 관리자 페이지 댓글 삭제
    @Transactional
    public Long deleteAdminComment(@PathVariable final Long id) {
        Comment entity = commentRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        entity.delete();
        return id;
    }

    // 댓글 리스트 조회 (부모 or 자식)
    public List<CommentListResponse> findAllComments(final CommentSearch params) {
        params.setMemberId(MemberUtil.getSessionMemberId());
        return commentMapper.findAllComments(params);
    }

}
