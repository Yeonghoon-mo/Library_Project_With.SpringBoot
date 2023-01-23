package com.library.domain.board;

import com.library.domain.attach.Attach;
import com.library.domain.attach.AttachRepository;
import com.library.domain.attach.AttachResponse;
import com.library.domain.attach.FileResponse;
import com.library.domain.member.Member;
import com.library.domain.member.MemberResponse;
import com.library.domain.member.MemberType;
import com.library.domain.menu.main.MainMenuResponse;
import com.library.domain.menu.sub.SubMenuResponse;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import com.library.utils.FileUtil;
import com.library.utils.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final AttachRepository attachRepository;

    private final BoardMapper boardMapper;

    private final FileUtil fileUtil;

    // 첨부파일 정보 생성
    private void saveAttachFiles(final List<FileResponse> attachFiles, final Board board) {

        if (CollectionUtils.isEmpty(attachFiles)) {
            return;
        }

        List<Attach> attachEntities = attachFiles.stream().map(attachFile -> {
            return Attach.builder()
                    .board(board)
                    .originalName(attachFile.getOriginalFilename())
                    .saveName(attachFile.getSaveFilename())
                    .size(attachFile.getFileSize())
                    .build();
        }).collect(Collectors.toList());

        attachRepository.saveAll(attachEntities);
    }

    // 첨부파일 삭제
    private void deleteAttachFiles(final List<Long> removeFileIds) {
        if (CollectionUtils.isEmpty(removeFileIds)) {
            return;
        }
        attachRepository.deleteAllByAttachIds(removeFileIds);
    }

    // 게시글 생성
    @Transactional
    public Long save(final BoardRequest params) {
        FileResponse repImage = fileUtil.uploadFile(params.getThumbnail()); // 대표 이미지 업로드
        List<FileResponse> attachFiles = fileUtil.uploadFiles(params.getAttachFiles()); // 첨부파일 업로드
        Member member = MemberUtil.getLoginSessionMember(); // 회원 (사용자 or 관리자)
        Board board = boardRepository.save(params.toEntity(member, repImage)); // 게시글 생성
        saveAttachFiles(attachFiles, board);// 첨부파일 정보 생성
        return board.getId();
    }

    // 게시글 수정
    @Transactional
    public Long update(final Long id, final BoardRequest params) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND)); // 게시글
        Member member = MemberUtil.getLoginSessionMember(); // 회원 (사용자 or 관리자)

        // 관리자가 아닌 경우, 권한 체크
        if (member.getType() != MemberType.ADMIN) {
            if (board.getMember().getId().longValue() != member.getId().longValue()) {
                throw new CustomException(ErrorCode.IS_NOT_CREATOR);
            }
        }

        FileResponse repImage = fileUtil.uploadFile(params.getThumbnail()); // 대표 이미지
        board.update(params, repImage); // 게시글 정보 수정
        List<FileResponse> attachFiles = fileUtil.uploadFiles(params.getAttachFiles()); // 첨부파일 업로드
        saveAttachFiles(attachFiles, board);// 첨부파일 정보 생성
        deleteAttachFiles(params.getRemoveFileIds()); // 첨부파일 삭제
        return id;
    }

    // 게시글 삭제
    @Transactional
    public Long delete(final Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND)); // 게시글
        Member member = MemberUtil.getLoginSessionMember(); // 회원 (사용자 or 관리자)

        // 관리자가 아닌 경우, 권한 체크
        if (member.getType() != MemberType.ADMIN) {
            if (board.getMember().getId().longValue() != member.getId().longValue()) {
                throw new CustomException(ErrorCode.IS_NOT_CREATOR);
            }
        }

        board.delete(); // 게시글 삭제
        attachRepository.deleteAttach(id); // 파일 삭제
        return id;
    }

    // 게시글 상세정보 조회
    public BoardResponse findById(final Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        MemberResponse member = new MemberResponse(board.getMember());
        board.increaseHits();
        return new BoardResponse(board, member);
    }

    // 게시글 상세정보 조회 (with. 좋아요)
    public BoardViewResponse findBoardWithLike(final Long boardId) {
        return boardMapper.findByBoardId(new BoardViewRequest(boardId, MemberUtil.getSessionMemberId()));
    }

    // 게시글 리스트 조회
    public Page<BoardResponse> findAll(final BoardInquirySearchCondition condition, final Pageable pageable){
        return boardRepository.findAll(condition, pageable).map(board -> {
            SubMenuResponse subMenuResponse = new SubMenuResponse(board.getBoardMenuNum());
            MainMenuResponse mainMenuResponse = new MainMenuResponse(board.getBoardMenuNum().getMainMenuId());
            MemberResponse memberResponse = new MemberResponse(board.getMember());
            return new BoardResponse(board, subMenuResponse, mainMenuResponse, memberResponse);
        });
    }

    // 게시글 인덱스 리스트
    public List<BoardResponse> findByBoardIndexList(final BoardInquirySearchCondition condition) {
        List<Board> list = boardRepository.findByBoardIndexList(condition);
        return list.stream().map(board -> {
            SubMenuResponse subMenuResponse = new SubMenuResponse(board.getBoardMenuNum());
            MainMenuResponse mainMenuResponse = new MainMenuResponse(board.getBoardMenuNum().getMainMenuId());
            MemberResponse memberResponse = new MemberResponse(board.getMember());
            return new BoardResponse(board, subMenuResponse, mainMenuResponse, memberResponse);
        }).collect(Collectors.toList());
    }

    // 공지사항 리스트
    public List<BoardResponse> findAllByNoticeYn(final String noticeYn) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id", "createdDate");
        List<Board> list = boardRepository.findAllByNoticeYn(noticeYn, sort);
        return list.stream().map(BoardResponse::new).collect(Collectors.toList());
    }

    // 파일 리스트 조회
    public List<AttachResponse> getAttachFileList(final Long boardId) {
        Long fileTotalCount = attachRepository.selectAttachTotalCount(boardId);
        if(fileTotalCount < 1) {
            return Collections.emptyList();
        }
        return attachRepository.findByAttachList(boardId).stream().map(AttachResponse::new).collect(Collectors.toList());
    }

    // 조회수 증가 ( 쿠키를 이용한 )
    @Transactional
    public void increaseHits(final Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.increaseHits();
    }

}
