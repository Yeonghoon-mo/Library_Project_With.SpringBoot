package com.library.domain.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInquirySearchCondition {

    // 페이징을 위한 변수들
    private Integer boardMenuNum;       // 게시판 Type
    private String searchType;          // 검색조건
    private String keyword;             // 검색 키워드
    private String noticeYn;            // 공지글 여부
    private String orderByName;         // 정렬 기준

    // 인덱스 리스트를 위한 변수들
    private Long startMenuNum;        // 리스트 시작 서브메뉴 번호
    private Long endMenuNum;          // 리스트 종료 서브메뉴 번호
    private Long listLimit;           // 리스트 노출 개수

}
