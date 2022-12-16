package com.library.domain.like;

import com.library.domain.board.Board;
import com.library.domain.board.BoardRepository;
import com.library.domain.board.BoardViewRequest;
import com.library.domain.member.Member;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import com.library.utils.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;


    // 좋아요 정보 저장 및 수정
    @Transactional
    public Long saveLike(final LikeRequest params) {

        Board board = boardRepository.findById(params.getBoardId()).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND)); // 게시글
        Member member = MemberUtil.getSessionMember(false); // 회원
        Like like = likeRepository.findLike(params.getBoardId(), member.getId()).orElse(null); // 좋아요

        // 좋아요 정보가 없는 경우, SAVE 후 현재 게시글 좋아요 정보 리턴
        if (like == null) {
            like = likeRepository.save(params.toEntity(board, member));
        } else {
            like.changeLikeType(params.getLikeType());
        }

        return like.getId();
    }

    // 좋아요 정보 조회
    public LikeCountResponse countByBoardId(final Long boardId) {
        return likeMapper.countByBoardId(new BoardViewRequest(boardId, MemberUtil.getSessionMemberId()));
    }

    // 좋아요 정보 삭제
    @Transactional
    public Long deleteLike(final Long boardId) {
        Like entity = likeRepository.findLike(boardId, MemberUtil.getSessionMemberId()).orElseThrow(() -> new CustomException(ErrorCode.LIKE_NOT_FOUND)); // 좋아요
        likeRepository.delete(entity);
        return boardId;
    }

}
