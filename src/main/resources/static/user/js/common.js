// 상단네비게이션
$(function(){
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
});

// 모바일 전체메뉴 클릭시
$(window).on("load", function(){
	// 모바일 전체메뉴 클릭시
	$("nav h2").click(function(){
		$("nav > div").toggleClass('mb_menu_top');
		$("nav > div > p").toggleClass('mb_menu_logo');
		$("nav #topmenu").toggleClass('mb_topmenu');
		$(".gnb_overlay").toggleClass('on');
		$(".toggleMenu button").toggleClass('on');
	});
	$('body').addClass("loaded");
});

// 클릭시 새창 팝업 띄우기
function popup_win(str,id,w,h,scrollchk){
	var pop = window.open(str,id,"width="+w+",height="+h+",scrollbars="+scrollchk+",resize=no,location=no ");
	pop.focus();
}

/* 메뉴바 FIXED */
if ($(window).width() > 0) {
	$(window).on("scroll",function(ev){
		if($(window).scrollTop() > 80 ) { /* 해당 높이를 벗어나면 fixed 클래스 추가 */
			$('#wrap header').addClass('fixed');
		}
		else{
			$('#wrap header').removeClass('fixed');
		}
		return false;
	});
}