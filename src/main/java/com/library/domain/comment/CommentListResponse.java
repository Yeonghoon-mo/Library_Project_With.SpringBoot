package com.library.domain.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponse {

    private Long commentId;
    private Long memberId;
    private String memberName;
    private String profileImage;
    private String content;
    private LocalDateTime createdDate;

}
