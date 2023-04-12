package com.library.domain.attach;

import com.library.status.YnStatus;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.library.domain.attach.QAttach.attach;

public class AttachRepositoryImpl implements AttachRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AttachRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // 파일 삭제 처리
    @Override
    public void deleteAttach(Long boardId) {
        queryFactory
                .update(attach)
                .set(attach.deleteYn, YnStatus.Y)
                .set(attach.modifiedDate, LocalDateTime.now())
                .where(attach.board.id.eq(boardId))
                .execute();
    }

    // 게시판 파일 삭제
    @Override
    public void deleteAllByAttachIds(List<Long> attachIds) {
        queryFactory
                .update(attach)
                .set(attach.deleteYn, YnStatus.Y)
                .where(attach.id.in(attachIds))
                .execute();
    }

//     파일 개수 조회
//    @Override
//    public Long selectAttachTotalCount(Long id) {
//        return queryFactory
//                .selectFrom(attach)
//                .where(attach.deleteYn.eq(YnStatus.N), attach.board.id.eq(id))
//                .fetchCount();
//    }

    // 파일 개수 조회
    @Override
    public Long selectAttachTotalCount(Long id) {
        return queryFactory
                .select(Wildcard.count)
                .from(attach)
                .where(attach.deleteYn.eq(YnStatus.N), attach.board.id.eq(id))
                .fetch().get(0);
    }

    // 파일 목록 조회
    @Override
    public List<Attach> findByAttachList(Long id) {
        return queryFactory
                .selectFrom(attach)
                .where(attach.deleteYn.eq(YnStatus.N), attach.board.id.eq(id))
                .fetch();
    }

}
