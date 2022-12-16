package com.library.domain.comment;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentResponse> findAllComment(CommentSearch params);

}
