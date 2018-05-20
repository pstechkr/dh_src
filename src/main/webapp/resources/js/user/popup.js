/* 
 * 유저 쪽 popup 관련 js파일.
 * Copyright(c) Jihan Jang. (TIEIN Communications) 
 */

$(document).ready(function(){
	
	// mask 클릭하면 fadeOut.
	$("#mask").on({
		click: function(){
			$(this).fadeOut();
		} // click end
	});
	
	// layer 클릭시 fadeOut되지 않기 위해.
	$("#mask #layer .inbox").on({
		click: function(){
			event.stopPropagation();
		}
	});
	
	// 쿠키가 있능감?
	if(getCookie("userid") != ""){
		$("#user_email").val(getCookie("userid"));
		$("#checkbox_user_email").attr("checked", true);
	}
	
});

function popuplogin(seq){
	request = {};
	user_email = $("#user_email").val();
	user_password = $("#user_password").val();	
	request["user_email"] = user_email;
	request["user_password"] = user_password;

	var isCheck = $("#checkbox_user_email").is(":checked"); 
	if(isCheck){
		setCookie("userid", $("#user_email").val().trim(), 30);
	}else{
		setCookie("userid", $("#user_email").val().trim(), 0);
	}
	$.ajax({
		url: "popuplogin",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){
        	if(response["isSuccess"] == "Y"){
        		// 로그인 성공
        		// 예약 로직 태우기.
        		if(response["location"] == "gathering"){
        			// confirm
        			confirmMethod(seq);
        		}else{
//        			reservationRequest();
        		}
        	}else{
        		// 로그인 실패
        		alert(response["msg"]);
        	}
        }, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}