/* 
 * 유저 쪽 Gathering 관련 js파일.
 * Copyright(c) Jihan Jang. (TIEIN Communications) 
 */

function reservationRequest(seq){
	
	// member check
	var _member = $("#member").val();
	if(_member == ""){
		alert("신청 인원을 입력해 주세요.");
		return false;
	}
	
	// 중복클릭 방지.
	$("button.btn_rsv").attr("onclick","").unbind("click");
	// ajax. 로그인 했는지 안했는지 체크하여 안한 경우 로그인 팝업창이 뜨도록 한다.
	$.ajax({
		url: "loginSessionCheck",
        type :"POST",
        
        contentType : "application/json;charset=UTF-8",
        success:function(response){
        	
        	if(response["sessionCheck"] == "Y"){
        		// confirm
        		confirmMethod(seq);
        	}else{
        		// mask width, height를 구함.
        		var maskWidth = $(document).width();
        		var maskHeight = $(document).height();
        		$("#mask").css({"width":maskWidth, "height":maskHeight});
        		// show
        		$("#mask").fadeIn();
        		// layer margin top 구함.
        		var top = ($(window).scrollTop() + ($(window).height() - $("#layer").height())/2);
        		$("#layer").css({"margin-top":top});
        		$("button.btn_rsv").attr("onclick","javascript:reservationRequest('" + seq + "');").unbind("click");
        	}
        },
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}

function confirmMethod(seq){
	
	var _member = $("#member").val();
	// confirm
	var $form = $("<form></form>");
	$form.attr("action", seq + "/confirm");
	$form.attr("method", "post");
	$form.appendTo("body");
	var member = $("<input type='hidden' name='member' value='" + _member + "'>");

	$form.append(member);
	$form.submit();
	
}
