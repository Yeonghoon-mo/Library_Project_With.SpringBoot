window.addEventListener('load', () => mainMenuList());
window.addEventListener('load', () => totalSearch());
window.addEventListener('load', () => addEnterTotalSearchEvent());

// 메인메뉴를 그리는 함수
async function mainMenuList() {
    const mainMenuUrl = '/api/main-menu?deleteYn=N';
    const subMenuUrl = '/api/sub-menu?deleteYn=N';

    await fetch(mainMenuUrl).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(json => {
        let html = '';

        if (!json.length) {
            alert('DB에 메뉴가 없습니다.');
        } else {
            json.forEach((obj, idx) => {
                html += `
                            <li class="has_sub" id="has_sub_${++idx}"><a href="${obj.url}">${obj.name}</a>`;
            });
        }
        document.getElementById('gnb').innerHTML = html;
        return fetch(subMenuUrl);
    }).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(json => {
        let subMenuHtml = '';


        subMenuHtml += '<ul>';
        json.forEach((obj) => {
            if(obj.mainMenuId.id === 1) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_1`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 2) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_2`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 3) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_3`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 4) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_4`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 5) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_5`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 6) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_6`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 7) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_7`).append(subMenuHtml);

        subMenuHtml += '<ul>';
        json.forEach((obj) => {

            if(obj.mainMenuId.id === 8) {
                subMenuHtml += `<li><a href="${obj.url}">${obj.name}</a></li>`;
            }
        });
        subMenuHtml += '</ul>';
        $(`#has_sub_8`).append(subMenuHtml);

        menuClickEvent();

    });
}

// 메인메뉴 아래의 서브메뉴를 그리는 함수
async function subMenuList() {
    const subMenuUri = '/api/sub-menu?deleteYn=N';
    let boardMenuNum = new URLSearchParams(location.search).get('boardMenuNum'); boardMenuNum *= 1;
    let mainMenuNum = new URLSearchParams(location.search).get('mainMenuNum'); mainMenuNum *= 1;

    await fetch(subMenuUri).then(response => {
        if(response.ok) {
            return response.json();
        }
    }).then(json => {
        let html = '';
        json.forEach((obj) => {
            const on = (obj.id === boardMenuNum) ? 'class="on"' : '';
            if(obj.mainMenuId.id === mainMenuNum) {
                html += `<a href="${obj.url}" ${on}>${obj.name}</a>`;
                document.getElementById('menulink').innerHTML = html;
            }

        })
    });
}

// 메뉴 이름을 상단 타이틀에 그리는 함수
async function titleSelect() {
    let boardMenuNum = new URLSearchParams(location.search).get('boardMenuNum'); boardMenuNum *= 1;
    let titleName = '';
    let subTitleName = '';

    fetch('/api/sub-menu?deleteYn=N').then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(json => {
        json.forEach((obj) => {
            if (boardMenuNum === obj.id) {
                titleName = `<h2>${obj.mainMenuId.name}</h2>`;
            }
        });
        json.forEach((obj) => {
            if(boardMenuNum === obj.id) {
                subTitleName = `<h3>${obj.name}</h3>`;
            }
        });

        document.getElementById('titleName').innerHTML = titleName;
        document.getElementById('subTitleName').innerHTML += subTitleName;
    });
}

// 게시판 제목 위의 대메뉴, 소메뉴 그리는 함수
async function menuSelect() {
    let boardMenuNum = new URLSearchParams(location.search).get('boardMenuNum'); boardMenuNum *= 1;

    let html = '';
    let titleName = '';

    fetch('/api/sub-menu?deleteYn=N').then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(json => {
        // 메인메뉴 그리기
        json.forEach((obj) => {
            if (boardMenuNum === obj.id) {
                html = `${obj.mainMenuId.name} - `;
                titleName = `<h2>${obj.mainMenuId.name}</h2>`;
            }
        });

        // 서브메뉴 그리기
        json.forEach((obj) => {
            if (boardMenuNum === obj.id) {
                html += obj.name;
            }
        });

        document.getElementById('subjectMenu').innerHTML = html;
        document.getElementById('titleName').innerHTML = titleName;
    });
}

// 전체검색(Total Search)에 대한 함수
function totalSearch() {
    // 최상단 검색어 입력 값 Input Text 에 넣는다.
    const keywordData = document.getElementById('allSearchKeyword').value;
    const allSearchKeyword = document.getElementById('keywordResult').innerText = keywordData;

    let html = `<a id="enterClickEvent" href="/board/search?keyword=${allSearchKeyword}" type="button" class="btns"><i class="fab fa-sistrix"></i><span class="skip_info">검색</span></a>`;
    document.getElementById('allSearchDiv').innerHTML = html;
}

// 키워드 (엔터 검색 이벤트 바인딩 - Total Search )
function addEnterTotalSearchEvent() {
    document.getElementById('allSearchKeyword').addEventListener('keyup', (e) => {
        if (e.keyCode === 13) {
            document.getElementById('enterClickEvent').click();
        }
    });
}

// 메인메뉴, 서브메뉴 메뉴 마우스 이벤트
function menuClickEvent() {
    // gnb
    $("nav #gnb > li").bind('focusin mouseenter',function() {
        $(this).find(' > ul').stop().fadeIn(200);
        $(this).children('a').addClass('on');
    })
    $("nav #gnb > li").bind('focusout mouseleave',function() {
        $(this).find(' > ul').stop().fadeOut(200);
        $(this).children('a').removeClass('on');
    })
    $("nav #gnb > li.has_sub > a").click(function(e){
        if (document.body.clientWidth < 980) {
            e.preventDefault();
            $("nav #gnb > li").unbind('focusout');
        }
    });

    // msearch_btn
    $(".msearch_btn").on("click", function() {
        $("#total_sch").toggleClass("mb_sch");
        $(".sch_overlay").toggleClass('on');
    });
    $(".close_btn").on("click", function() {
        $("#total_sch").toggleClass("mb_sch");
        $(".sch_overlay").toggleClass('on');
    });
}