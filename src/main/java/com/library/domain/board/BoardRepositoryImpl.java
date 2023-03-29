package com.library.domain.board;

import com.library.status.YnStatus;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.library.domain.board.QBoard.board;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 게시판 페이징, 검색
     */
    @Override
    public Page<Board> findAll(BoardInquirySearchCondition condition, Pageable pageable) {
        List<Board> list = queryFactory
                .selectFrom(board)
                .where(board.deleteYn.eq(YnStatus.N)
                        .and(search(condition.getSearchType(), condition.getKeyword()))
                        .and(boardMenuNum(condition.getBoardMenuNum())))
                .orderBy(board.noticeYn.desc(), orderByNameType(condition.getOrderByName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Board> count = queryFactory
                .selectFrom(board)
                .where(board.deleteYn.eq(YnStatus.N)
                        .and(search(condition.getSearchType(), condition.getKeyword()))
                        .and(boardMenuNum(condition.getBoardMenuNum())));

        return PageableExecutionUtils.getPage(list, pageable, count::fetchCount);

    }

    @Override
    public List<Board> findByBoardIndexList(BoardInquirySearchCondition condition) {

        List<Board> list = queryFactory
                .selectFrom(board)
                .where(board.deleteYn.eq(YnStatus.N)
                        .and(board.boardMenuNum.id.goe(condition.getStartMenuNum()))
                        .and(board.boardMenuNum.id.loe(condition.getEndMenuNum())))
                .orderBy(orderByNameType(condition.getOrderByName()))
                .limit(condition.getListLimit())
                .fetch();

        return list;
    }


    /**
     * 게시글 검색 조건
     */
    private BooleanExpression search(String searchType, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return null;
        } else if (StringUtils.isBlank(searchType)) {
            return board.title.contains(keyword).or(board.content.contains(keyword).or(board.writer.contains(keyword)));
        }

        switch (searchType) {
            case "title":
                return board.title.contains(keyword);
            case "content":
                return board.content.contains(keyword);
            case "writer":
                return board.writer.contains(keyword);
        }
        return null;
    }

    /**
     * 게시판 타입
     */
    private BooleanExpression boardMenuNum(Integer boardMenuNum) {
        if (boardMenuNum == null) {
            return null;
        } else if (boardMenuNum == 19970709) {
            return board.boardMenuNum.id.notIn(28, 29, 30);
        }
        return board.boardMenuNum.id.eq(boardMenuNum);
    }

    /**
     * 정렬 기준
     */
    private OrderSpecifier<?> orderByNameType(String orderByName) {
        if (StringUtils.isBlank(orderByName)) {
            return board.id.desc();
        }
        switch (orderByName) {
            case "general":
                return board.id.desc();
            case "hits":
                return board.hits.desc();
            case "createdDate":
                return board.createdDate.desc();
        }

        return null;
    }

}
