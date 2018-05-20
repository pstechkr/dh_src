function popupfacebooklogin(seq){
	
	alert("?");
	if(navigator.userAgent.match('CriOS')){
		var member = $("#member").val();
		var permissionUrl = "https://www.facebook.com/dialog/oauth?client_id=" + "201629896706999" + "&response_type=code&redirect_uri=" + "https://elever.kr/fbchrome?item=" + seq + "," + member + "&scope=email,user_birthday";
		window.location = permissionUrl;
    return;
    
	}else{
		//페이스북 로그인 버튼을 눌렀을 때의 루틴.
	    FB.login(function(response) {
	    	
	    	try{
	    		FB.api('/me', function(user){
	    	    	// 중복클릭 방지.
	    			$(".login_button>button.facebook").attr("onclick","").unbind("click");
	    			popupfacebookRegister(user, seq);
	    		});
	    	}catch(err){
	    		// 취소 누른 경우
	    		alert("Facebook 정보를 불러올 수 없습니다.");
	    		$(".login_button>button.facebook").attr("onclick","javascript:popupfacebooklogin(" + seq + ");").bind("click");
	    		return false;
	    	}
		}, {scope:"email,user_birthday"});
	}
}
function popupfacebookRegister(user, seq){
	
	request = {};
	request["user_email"] = user.email;
	request["user_name"] = user.name;
	request["user_birthday"] = user.birthday;
	request["user_gender"] = user.gender;
	request["facebook_id"] = user.id;
	
	if(user.id == null){
		alert("Facebook 정보를 불러올 수 없습니다.");
		$(".login_button>button.facebook").attr("onclick","javascript:popupfacebooklogin(" + seq + ");").bind("click");
		return false;
	}
	if(user.email == null){
		alert("Facebook 정보를 불러올 수 없습니다.");
		$(".login_button>button.facebook").attr("onclick","javascript:popupfacebooklogin(" + seq + ");").bind("click");
		return false;
	}
	
	$.ajax({
		url: "popupfacebooklogin",
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
        			// reservationRequest();
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




function facebooklogin() {  
	if(navigator.userAgent.match('CriOS')){
		var permissionUrl = "https://www.facebook.com/dialog/oauth?client_id=" + "201629896706999" + "&response_type=code&redirect_uri=" + "https://elever.kr/fbchrome" + "&scope=email,user_birthday";
//		var permissionUrl = "https://www.facebook.com/dialog/oauth?client_id=" + "271344896402165" + "&response_type=code&redirect_uri=" + "https://220.117.33.89:8443/eleverweb/fbchrome" + "&scope=email,user_birthday";
		window.location = permissionUrl;
    return;
    
	}else{
		//페이스북 로그인 버튼을 눌렀을 때의 루틴.  
	    FB.login(function(response) {
	    	try{
	    		FB.api('/me', function(user){
	    	    	// 중복클릭 방지.
	    			$(".login_button>button.facebook").attr("onclick","").unbind("click");
	    			facebookRegister(user);
	    		});
	    	}catch(err){
	    		// 취소 누른 경우
	    		alert("Facebook 정보를 불러올 수 없습니다.");
	    		$(".login_button>button.facebook").attr("onclick","javascript:facebooklogin();").bind("click");
	    		return false;
	    	}
	    	
		}, {scope:"email,user_birthday"});
	}
}
function facebookRegister(user){
	var user_email = $("<input type='hidden' name='user_email' value='" + user.email + "'>");
	var user_name = $("<input type='hidden' name='user_name' value='" + user.name + "'>");
	var user_birthday = $("<input type='hidden' name='user_birthday' value='" + user.birthday + "'>");
	var user_gender = $("<input type='hidden' name='user_gender' value='" + user.gender + "'>");
	var facebook_id = $("<input type='hidden' name='facebook_id' value='" + user.id + "'>");

	if(user.id == null){
		alert("Facebook 정보를 불러올 수 없습니다.");
		$(".login_button>button.facebook").attr("onclick","javascript:facebooklogin();").bind("click");
		return false;
	}
	if(user.email == null){
		alert("Facebook 정보를 불러올 수 없습니다.");
		$(".login_button>button.facebook").attr("onclick","javascript:facebooklogin();").bind("click");
		return false;
	}
	
	// form 생성
	var $form = $("<form></form>");
	$form.attr("action", "facebookLogin");
	$form.attr("method", "post");
	$form.appendTo("body");
	
	$form.append(user_email).append(user_name).append(user_birthday).append(user_gender).append(facebook_id);
	$form.submit();
}