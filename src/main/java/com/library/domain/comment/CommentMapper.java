package com.library.domain.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentListResponse> findAllComments(final CommentSearch params);

}
