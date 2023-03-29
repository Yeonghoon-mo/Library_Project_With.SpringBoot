window.addEventListener('load', () => menuOnClassInsert());

// 최상단 메뉴 그리는 함수 ( ex) 기준정보관리, 회원관리 ) With. 하드코딩
function menuOnClassInsert() {
    let mnId = new URLSearchParams(location.search).get('mnId');
    let html = '';

    if (mnId === '1') {
        html = `<a href="/manager/manager?mnId=1" class="on"><i class="fas fa-info-circle"></i>기준정보관리</a>
						<ul>
							<li><a href="/manager/manager?mnId=1">관리자 관리</a></li>
						</ul>`;
        document.getElementById('depth1-menu1').innerHTML = html;
    }

    if (mnId === '2') {
        html = `<a href="/manager/member-list?mnId=2" class="on"><i class="fas fa-user-edit"></i>회원관리</a>
						<ul>
							<li><a href="/manager/member-list?mnId=2">회원관리</a></li>
							<li><a href="/manager/regular-member-authority?mnId=2">정회원 가입 요청관리</a></li>
						</ul>`;
        document.getElementById('depth1-menu2').innerHTML = html;
    }

    if (mnId === '3') {
        html = `<a href="/manager/board-manager-list?mnId=3&mainMenuId=1&boardMenuNum=1&boardType=0" class="on"><i class="fas fa-chart-line"></i>게시판관리</a>
						<ul>
							<li><a href="/manager/board-manager-list?mainMenuId=1&boardMenuNum=1&mnId=3&boardType=0">게시판</a></li>
							<li><a href="/manager/board-manager-list?mainMenuId=2&boardMenuNum=5&mnId=3&boardType=1">썸네일형 게시판</a></li>
						</ul>`;
        document.getElementById('depth1-menu3').innerHTML = html;
    }

    mouseClickEvent();
}

// 서브메뉴 그리는 함수
async function subMenuList() {
    const mainMenuUri = '/api/main-menu?deleteYn=N';
    const subMenuUri = '/api/sub-menu?deleteYn=N';
    let mainMenuId = new URLSearchParams(location.search).get('mainMenuId');
    mainMenuId *= 1;
    let boardMenuNum = new URLSearchParams(location.search).get('boardMenuNum');
    boardMenuNum *= 1;
    let boardType = new URLSearchParams(location.search).get('boardType');
    boardType *= 1;
    let subTitleHtml = '';

    fetch(mainMenuUri).then(response => {
        if (response.ok) {
            return response.json();
        }
    }).then(json => {
        // 1차 대메뉴
        json.forEach((obj) => {
            if (mainMenuId === obj.id) {
                subTitleHtml += `${obj.name}  / `;
            }
        });

        return fetch(subMenuUri);
    }).then(response => {
        return response.json();
    }).then(json => {
        let html = '';
        // 서브메뉴
        json.forEach((obj) => {
            const on = (obj.id === boardMenuNum) ? 'class="on"' : '';
            if (obj.mainMenuId.id === mainMenuId) {
                html += `<li><a href="/manager/board-manager-list?mainMenuId=${obj.mainMenuId.id}&boardMenuNum=${obj.id}&mnId=3&boardType=${boardType}"${on}>${obj.name}</a></li>`;
            }
        });
        document.getElementById('menulink').innerHTML = html;

        // 2차 소메뉴
        json.forEach((obj) => {
            if (obj.id === boardMenuNum) {
                subTitleHtml += ` ${obj.name}`;
            }
        });

        document.getElementById('subtitle1').innerHTML = subTitleHtml;
    });
}

// 마우스 클릭 이벤트
function mouseClickEvent() {
    $(document).ready(function () {
        // gnb
        $("#gnb .menu > li > a").bind('focusin mouseenter', function () {
            $(this).parent().find("ul").fadeIn();
            $(this).addClass('on');
            $(this).parent().siblings().find("ul").fadeOut(300);
            $(this).parent().siblings().find("a").removeClass('on');
        });
        if (!$(this).hasClass("on")) {
            $('#menu li a.on').next('ul').css("display", "block");
        }
        // menu_toggle
        $(".menu_toggle").click(function () {
            $('#container .menu_toggle').toggleClass('active');
            $('body').toggleClass('snb_none');
            $(window).trigger('resize');
        });
        // left_menu
        $("#snb > ul > li.has_sub > a").click(function (e) {
            if ($(this).parent().has("ul")) {
                e.preventDefault();
            }

            if (!$(this).hasClass("open")) {
                $(this).next("ul").slideUp(350);
                $(this).addClass("open");
            } else if ($(this).hasClass("open")) {
                $(this).removeClass("open");
                $(this).next("ul").slideDown(700);
            }
        });
        // cm_list
        $(".cm_list > div > a").click(function () {
            var submenu = $(this).next("div.hide_view");
            if (submenu.is(":visible")) {
                submenu.removeClass("open");
            } else {
                submenu.addClass("open");
            }
        });
    });
}
