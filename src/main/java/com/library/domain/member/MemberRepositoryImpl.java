package com.library.domain.member;

import com.library.status.YnStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.library.domain.member.QMember.member;
import static com.library.domain.regularmember.QRegularMember.regularMember;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * Projections Method
     */
    private QBean<MemberResponse> projectionMethod() {
        return Projections.fields(MemberResponse.class,
                member.id,
                member.type,
                member.loginId,
                member.name,
                member.profileImage,
                member.authorityYn,
                member.deleteYn,
                member.createdDate,
                member.modifiedDate,
                regularMember.phone
        );
    }

    /**
     * 회원 페이징, 검색
     *
     * @param condition
     * @param pageable
     * @return
     */
    @Override
    public Page<MemberResponse> findAll(MemberInquirySearchCondition condition, Pageable pageable) {
        List<MemberResponse> list = queryFactory
                .select(projectionMethod())
                .from(member)
                .leftJoin(regularMember)
                .on(member.id.eq(regularMember.member.id))
                .where(deleteYnEq(condition.getDeleteYn())
                        .and(search(condition.getSearchType(), condition.getKeyword()))
                        .and(memberTypeEq(condition.getMemberType()))
                        .and(authorityYn(condition.getAuthorityYn())))
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Member> count = queryFactory
                .selectFrom(member)
                .where(deleteYnEq(condition.getDeleteYn())
                        .and(search(condition.getSearchType(), condition.getKeyword()))
                        .and(memberTypeEq(condition.getMemberType()))
                        .and(authorityYn(condition.getAuthorityYn())));

        return PageableExecutionUtils.getPage(list, pageable, count::fetchCount);
    }

    /**
     * 회원 상세페이지
     *
     * @param id
     * @return
     */
    @Override
    public Optional<MemberResponse> findByMemberId(Long id) {
        MemberResponse entity = queryFactory
                .select(projectionMethod())
                .from(member)
                .leftJoin(regularMember)
                .on(member.id.eq(regularMember.member.id))
                .where(member.id.eq(id))
                .fetchFirst();

        return Optional.ofNullable(entity);
    }

    @Override
    public Member findByLoginId(String loginId) {
        return queryFactory
                .selectFrom(member)
                .where(member.loginId.eq(loginId), member.deleteYn.eq(YnStatus.N))
                .fetchFirst();
    }

    @Override
    public Member findByMemberTypeLoginId(LoginRequest params) {
        return queryFactory
                .selectFrom(member)
                .where(member.loginId.eq(params.getLoginId()),
                        member.deleteYn.eq(YnStatus.N),
                        memberType(params.getMemberType()),
                        authorityYn(params.getAuthorityYn()))
                .fetchFirst();
    }


    /**
     * 회원 검색 조건
     *
     * @param searchType
     * @param keyword
     * @return
     */
    private BooleanExpression search(String searchType, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return null;
        } else if (StringUtils.isBlank(searchType)) {
            return member.loginId.contains(keyword).or(member.name.contains(keyword));
        }

        switch (searchType) {
            case "loginId":
                return member.loginId.contains(keyword);
            case "name":
                return member.name.contains(keyword);
        }

        return null;
    }

    private BooleanExpression memberTypeEq(MemberType memberType) {
        if (memberType == null) {
            return member.type.ne(MemberType.ADMIN);
        }
        return member.type.eq(memberType);
    }

    /**
     * 회원 유형 구분
     *
     * @param memberType
     * @return
     */
    private BooleanExpression memberType(MemberType memberType) {
        if (memberType == null) {
            return member.type.eq(MemberType.GENERAL).or(member.type.eq(MemberType.REGULAR)); // NULL의 경우, 관리자를 제외한 일반, 정회원 검색
        }
        return member.type.eq(memberType);
    }

    /**
     * 삭제 여부에 상관없이 출력 ( 관리자 사용, 미사용 표기를 위함 )
     *
     * @param deleteYn
     * @return
     */
    private BooleanExpression deleteYnEq(YnStatus deleteYn) {
        if (deleteYn == null) {
            return member.deleteYn.eq(YnStatus.Y).or(member.deleteYn.eq(YnStatus.N));
        }
        return member.deleteYn.eq(deleteYn);
    }

    /**
     * 정회원 회원가입 승인여부
     */
    private BooleanExpression authorityYn(String authorityYn) {
        if (authorityYn == null || authorityYn.equals("Y")) {
            return member.authorityYn.eq("Y");
        }
        return member.authorityYn.eq(authorityYn);
    }
}
