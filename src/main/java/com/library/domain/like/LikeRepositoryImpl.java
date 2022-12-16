package com.library.domain.like;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.library.domain.like.QLike.like;


public class LikeRepositoryImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LikeRepositoryImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // 게시글 번호, 세션 안에있는 회원 아이디를 이용해 해당 게시글 좋아요 수 체크
    @Override
    public Optional<Like> findLike(Long boardId, Long memberId) {
            Like entity = queryFactory
                    .selectFrom(like)
                    .where(
                           like.board.id.eq(boardId),
                           like.member.id.eq(memberId)
                    )
                    .fetchFirst();
            return Optional.ofNullable(entity);
    }

}
