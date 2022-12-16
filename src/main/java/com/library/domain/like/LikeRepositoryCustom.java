package com.library.domain.like;

import java.util.Optional;

public interface LikeRepositoryCustom {

    Optional<Like> findLike(Long boardId, Long memberId);

}
