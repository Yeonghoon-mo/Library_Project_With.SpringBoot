package com.library.domain.like;

import lombok.Getter;

@Getter
public class LikeCountResponse {

    private int likeCnt;
    private int badCnt;
    private Integer likeType;
}
