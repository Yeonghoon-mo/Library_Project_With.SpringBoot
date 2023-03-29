package com.library.domain.comment;

import com.library.status.YnStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.library.domain.comment.QComment.comment;
import static com.library.domain.member.QMember.member;

public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<CommentResponse> findAllComment(CommentSearch params) {
        return queryFactory
                .select(Projections.fields(CommentResponse.class,
                        comment.id,
                        comment.board.id.as("boardId"),
                        comment.member.id.as("memberId"),
                        member.name.as("memberName"),
                        member.profileImage.as("memberProfileImage"),
                        comment.parentCommentId,
                        comment.commentLevel,
                        comment.content,
                        comment.deleteYn,
                        comment.createdDate
                ))
                .from(comment)
                .innerJoin(member).on(comment.member.id.eq(member.id))
                .where(
                        comment.board.id.eq(params.getBoardId()),
                        comment.commentLevel.eq(params.getCommentLevel()),
                        comment.deleteYn.eq(YnStatus.N),
                        parentCommentIdEq(params.getParentCommentId())
                )
                .orderBy(comment.id.desc())
                .fetch();
    }

    private BooleanExpression parentCommentIdEq(Long parentCommentId) {
        return (parentCommentId == null) ? null : comment.parentCommentId.eq(parentCommentId);
    }


}
