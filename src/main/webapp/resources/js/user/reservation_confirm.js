/* 
 * 유저 쪽 상품예약 관련 js파일.
 * Copyright(c) Jihan Jang. (TIEIN Communications) 
 */

$(document).ready(function(){
	
	// 예약자정보 radio button
	$("input:radio[name='reservation_member_check']").eq(0).on({
		click: function(){
			$(".reservation_name input").val($(".user_name dd").text());
			$(".reservation_phoneno input").val($(".user_phoneno dd").text());
			$(".reservation_email input").val($(".user_email dd").text());
		}
	});
	$("input:radio[name='reservation_member_check']").eq(1).on({
		click: function(){
			$(".reservation_name input").val("");
			$(".reservation_phoneno input").val("");
			$(".reservation_email input").val("");
		}
		
	});
});

function confirm(seq){
	
	alert("?!");
	
	var $form = $("<form></form>");
	$form.attr("action", "confirmCommit");
	$form.attr("method", "post");
	$form.appendTo("body");
	
	req_seq = $("<input type='hidden' name='schedule_seq' value='" + seq + "'>");
	$form.append(req_seq);
	$form.submit();
}
