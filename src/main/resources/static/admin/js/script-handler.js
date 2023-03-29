// document.getElementById
function findById(id) {
    return document.getElementById(id);
}

/**
 * 페이지 번호 HTML draw
 페이지 번호(page)
 페이지당 출력할 데이터 개수(size)
 하단에 출력할 페이지 개수(pageSize)
 전체 데이터 수(totalElements)
 전체 페이지 수(totalPages)
 */
function drawPages(paginationInfo, findPaginationAll) {
    const pageParam = new URLSearchParams(location.search).get('search');

    // 전체 페이지 수
    let totalPages = parseInt((paginationInfo.totalElements - 1) / paginationInfo.size) + 1;

    // 전체 데이터 개수 저장 ( 통합검색에 사용 )
    if (pageParam === 'on') {
        let totalCount = `${paginationInfo.totalElements}개`;
        document.getElementById('emtmt').innerText = totalCount;
    }

    // 현재 페이지 번호가 전체 페이지 수보다 크면, 현재 페이지 번호에 전체 페이지 수 저장
    if (paginationInfo.page > paginationInfo.totalPages) {
        paginationInfo.page = paginationInfo.totalPages;
    }

    // 페이지 리스트의 첫 페이지 번호
    let startPage = parseInt((paginationInfo.page - 1) / paginationInfo.pageSize) * paginationInfo.pageSize + 1;

    // 페이지 리스트의 마지막 페이지 번호
    let endPage = parseInt(startPage + paginationInfo.pageSize) - 1;

    // 마지막 페이지 번호가 전체 페이지 수보다 크면, 마지막 페이지 번호에 전체 페이지 수 저장
    if (endPage > paginationInfo.totalPages) {
        endPage = paginationInfo.totalPages;
    }

    // 이전 페이지 존재 여부
    const existPrevPage = startPage !== 1;

    // 다음 페이지 존재 여부
    const existNextPage = (endPage * paginationInfo.size) < paginationInfo.totalElements;

    let html = '';

    // 첫 페이지, 이전 페이지
    if (existPrevPage) {
        html += `
            <a href="javascript: void(0);" onclick="${findPaginationAll}(1);" class="page_bt first">첫페이지 </a>
            <a href="javascript: void(0);" onclick="${findPaginationAll}(${startPage - 1});" class="page_bt prev">이전 페이지 그룹</a>
		`;
    }

    // 페이지 번호
    for (let i = startPage; i <= endPage; i++) {
        const active = (i === paginationInfo.page) ? 'class="on"' : '';
        html += `<a href="javascript: void(0);" ${active} onclick="${findPaginationAll}(${i});">${i}</a>`;
    }

    // 다음 페이지, 마지막 페이지
    if (existNextPage) {
        html += `
            <a href="javascript: void(0);" onclick="${findPaginationAll}(${endPage + 1});" class="page_bt next">다음 페이지 그룹</a>
            <a href="javascript: void(0);" onclick="${findPaginationAll}(${paginationInfo.totalPages});" class="page_bt last">마지막페이지</a>
        `;
    }

    document.querySelector('.paging > ul').innerHTML = html;
}

// 페이지 번호 HTML remove
function removePages() {
    document.querySelector('.paging > ul').innerHTML = '';
}

/**
 * 조회 API 호출 함수
 * @param uri  - 필요한 URI 값
 * @param params - Query String 에 존재하는 uri 값 호출
 * @returns {Promise<any>}
 */
async function getJson(uri, params) {
    if (params) {
        uri = uri + '?' + new URLSearchParams(params).toString();
    }

    const response = await fetch(uri);

    if (!response.ok) {
        await response.json().then(error => {
            throw error;
        });
    }

    return await response.json();
}

// 로그아웃 (With. Admin)
function logoutAdmin() {
    fetch('/api/members/logout')
        .then(response => {
            if (!response.ok) {
                throw new Error("Request failed...");
            }
            location.assign("/manager");
        }).catch(error => {
        console.log(error);
    });
}

// 로그아웃 (With. Member)
function logoutMember() {
    fetch('/api/members/logout')
        .then(response => {
            if (!response.ok) {
                throw new Error("Request failed...");
            }
            location.assign("/login");
        }).catch(error => {
        console.log(error);
    });
}

// 업로드 파일명 렌더링 함수
function changeFileName(file, fileId) {
    if (fileId) {
        removeFileIds.add(fileId);
    }
    file = $(file);
    const filename = file[0].files[0].name;
    const target = file.parent().prev();
    target.val(filename);

    target.prev().remove();
}

// 문자열을 HTML로 변환
function unescape(str) {
    const doc = new DOMParser().parseFromString(str, 'text/html');
    return doc.documentElement.textContent;
}

// 키워드 (엔터 검색 이벤트 바인딩 - Pagination)
function addEnterSearchEvent() {
    document.getElementById('keyword').addEventListener('keyup', (e) => {
        if (e.keyCode === 13) {
            findPaginationAll(1);
        }
    });
}

/**
 * API 호출
 * @param uri - Request URI
 * @param method - Request Method
 * @param params - Parameters
 * @param isFormData - FormData 여부
 * @returns json
 */
async function callApi(uri, method, params, isFormData) {

    const options = {method: method};

    if (!isFormData) {
        options.headers = {'Content-Type': 'application/json'};
    }

    if (params !== undefined) {
        options.body = (!isFormData) ? JSON.stringify(params) : params;
    }

    const response = await fetch(uri, options);

    if (!response.ok) {
        await response.json().then(error => {
            throw error;
        });
    }

    return await response.json();
}

// 파일 삭제 처리용 익명함수
const removeFileIds = (function () {
    const ids = [];
    return {
        add(id) {
            ids.push(id);
        },
        getAll() {
            return ids;
        }
    }
}());

// 파일 시퀀스 처리용 익명함수
const fileSeq = (function () {
    let seq = 0;
    return {
        up() {
            return ++seq;
        },
        get() {
            return seq;
        }
    }
}());


