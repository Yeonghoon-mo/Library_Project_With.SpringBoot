package com.library.domain.attach;

import com.library.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachRequest {

    private Board board; // 게시글 번호(FK)
    private String originalName; // 원본 파일명
    private String saveName; // 저장 파일명
    private Long size; // 파일 사이즈

    @Builder
    public Attach toEntity() {
        return Attach.builder()
                .board(board)
                .originalName(originalName)
                .saveName(saveName)
                .size(size)
                .build();
    }
}
