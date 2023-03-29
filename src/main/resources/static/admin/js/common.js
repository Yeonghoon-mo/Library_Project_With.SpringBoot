// 레이어 팝업(기본)
function layerPop(popName) {
    var $layer = $("#" + popName);
    $layer.addClass("on");
    $layer.fadeIn(500).css('display', 'inline-block').wrap('<div class="overlay_t"></div>');
    $('body').css('overflow', 'hidden');

    /* $('.overlay_t').click(function(e) {
        if (e.target === this) {
            $(".popLayer").hide().unwrap( '<div class="overlay_t"></div>');
            $('body').css('overflow','');
            $(".popLayer video").each(function() { this.pause(); this.load(); });
        }
    }); */
}

function layerPopClose() {
    $(".popLayer").hide().unwrap('<div class="overlay_t"></div>');
    $('body').css('overflow', 'auto');
    $(".popLayer video").each(function () {
        this.pause();
        this.load();
    });
}

// 클릭시 새창 팝업 띄우기
function popup_win(str, id, w, h, scrollchk) {
    var pop = window.open(str, id, "width=" + w + ",height=" + h + ",scrollbars=" + scrollchk + ",resize=no,location=no ");
    pop.focus();
}