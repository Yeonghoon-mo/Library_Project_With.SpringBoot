$(document).ready(function(){
	// faq
	$("#faq_nav > li > a").click(function(e){
		if($(this).parent().has("> div")) {
			e.preventDefault();
		}

		if(!$(this).hasClass("open")) {
			// hide any open menus and remove all other classes
			$("#faq_nav li > div").slideUp(700);
			$("#faq_nav li a").removeClass("open");

			// open our new menu and add the open class
			$(this).next("div").slideDown(350);
			$(this).addClass("open");
		}else if($(this).hasClass("open")) {
			$(this).removeClass("open");
			$(this).next("div").slideUp(700);
		}
	});
	
	// path
	$("#path span").next("ul").hide();	
	$("#path > li > ul").prev("span").on("click", function() {
		$(this).parent().siblings().find(" > span").removeClass("on").end().find(" > ul").hide();
		$(this).toggleClass("on");
		$(this).next("ul").toggle();
		return false;
	});	
	$("#path").mouseleave(function(){
		$("#path > li > span").removeClass("on").next("ul").hide();
	});
	
	// snav
	$(".tabnav > button").next("div").hide();	
	$(".tabnav > div").prev("button").on("click", function() {
		$(this).parent().siblings().find(" > button").removeClass("on").end().find(" > div").hide();
		$(this).toggleClass("on");
		$(this).next("div").toggle();
		return false;
	});
	/* $(".tabnav > div").on("click", function() {
		$(this).hide();
		$('.tabnav > button').toggleClass("on");	
		return false;
	}); */
	// snav_title
	var _select_title = $(".tabnav > button span");
	$(".smenu li button").on('click', function () {
		var _li_value = $(this).text();
		_select_title.text(_li_value);
	});
	
	// 체크박스 전체선택
	$('#chk_all').click(function () {
		$(".agree_in input[type='checkbox']").prop('checked', ($('#chk_all').prop("checked")));
	});
	$(".agree_in input[type='checkbox']").on('click', function () {
		var allCnt = $(".agree_in input[type='checkbox']").length;
		var checkedCnt = $(".agree_in input[type='checkbox']").filter(":checked").length;
		$("#chk_all").prop('checked', (allCnt == checkedCnt));
	});
	
	// 댓글
	$(".cm_re_info > button").click(function(){
		var submenu = $(this).parent().next("div.hide_view");
		if( submenu.is(":visible") ){
			submenu.removeClass("open");
		}else{
			submenu.addClass("open");
		}
	});	
	
	// iframe
	$( '.bbs_wrap iframe, .view_con iframe, .media_wrap iframe' ).wrap( '<div class="youtubeWrap"></div>' );
	
	$( '.media_wrap video' ).wrap( '<div class="videoWrap"></div>' );

	$(".media_wrap .close_btn").click(function () {
		$("#main_section .media_wrap").css('display', 'none');
		$(".media_wrap video").each(function() {
			this.pause();
			// this.load(); 
		});
	});
	
	// 첨부파일
	$(".file_input input[type='file']").on('change',function(){
		var fileName = $(this).val().split('/').pop().split('\\').pop();
		$(this).parent().siblings("input[type='text']").val(fileName);
	});
	// 파일업로드 미리보기
	$('.file_upload input[type=file]').change(function(event) {
		var tmppath = URL.createObjectURL(event.target.files[0]);
		$(this).parent('label').parent('.file_upload').parent('.file_preview').find("img").attr('src',tmppath);
	});
	
	// 이메일 인증
	$(".emailchk").on("click", function() {
		$(".emailchk_num").fadeIn();
	});
	
	// 라디오 버튼 체크 이벤트
	$('#catelist span').on('click', function(){
		$(":radio[name='catepick']").change(function(){
			var tabcont = $('.tabcont');
			var catepick = $(this).prop("checked",true).attr("id");
			tabcont.removeClass('on');
			$('.'+catepick).addClass('on');
		});
	});

});

// 레이어 팝업(기본)
function layerPop(popName){
	var $layer = $("#"+ popName);
	$layer.addClass("on");
	$layer.fadeIn(500).css('display', 'inline-block').wrap( '<div class="overlay_t"></div>');
	$('body').css('overflow','hidden');
	
	$('.overlay_t').click(function(e) {
		if (e.target === this) {
			$(".popLayer").hide().unwrap( '<div class="overlay_t"></div>');
			$('body').css('overflow','');
			$(".popLayer video").each(function() { this.pause(); this.load(); });
		}
	});
}
function layerPopClose(){
	$(".popLayer").hide().unwrap( '<div class="overlay_t"></div>');
	$('body').css('overflow','auto');
	$(".popLayer video").each(function() { this.pause(); this.load(); });
}

// 탭메뉴
function openType(evt, hsName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontainer");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(hsName).style.display = "block";
  evt.currentTarget.className += " active";	
}

// 셀렉트스타일
var _select_title = $(".text_ul_wrap > a");

_select_title.click(function () {
  $(".ul_select_style").toggleClass("active");
  $(".select_icon").toggleClass("active");
});

$(".ul_select_style > li").on('click', function () {
  var _li_value = $(this).text();
  _select_title.text(_li_value);
  $(".ul_select_style").removeClass("active");
  $(".select_icon").toggleClass("active");
});
$("body").click(function (e) {
  if ($(".ul_select_style").hasClass("active")) {
    if (!$(".text_ul_wrap").has(e.target).length) {
      $(".ul_select_style").removeClass("active");
      $(".select_icon").removeClass("active");
    }
  }
});
