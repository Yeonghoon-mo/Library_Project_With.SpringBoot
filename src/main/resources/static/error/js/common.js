$(document).ready(function () {
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

// 클릭시 새창 팝업 띄우기
function popup_win(str, id, w, h, scrollchk) {
    var pop = window.open(str, id, "width=" + w + ",height=" + h + ",scrollbars=" + scrollchk + ",resize=no,location=no ");
    pop.focus();
}