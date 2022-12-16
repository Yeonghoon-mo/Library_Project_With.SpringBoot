package com.library.domain.like;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeApiController {

    private final LikeService likeService;

    // 좋아요 정보 저장 및 수정
    @PostMapping
    public Long saveLike(@RequestBody final LikeRequest params) {
        return likeService.saveLike(params);
    }

    // 좋아요 정보 조회
    @GetMapping
    public LikeCountResponse countByBoardId(@RequestParam final Long boardId) {
        return likeService.countByBoardId(boardId);
    }

    // 좋아요 정보 삭제
    @DeleteMapping
    public Long deleteLike(@RequestBody final Long boardId) {
        return likeService.deleteLike(boardId);
    }

}
