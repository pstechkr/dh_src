$(document).ready(function(){
	// 쿠키가 있능감?
	if(getCookie("userid") != ""){
		$("#user_email").val(getCookie("userid"));
		$("#checkbox_user_email").attr("checked", true);
	}
});
// 로그인
function login(){
	
	// 유효성 체크
	if($("#user_email").val() == ""){alert("이메일을 입력해 주세요.");return false;};
	if($("#user_password").val() == ""){alert("비밀번호를 입력해 주세요.");return false;};

	var isCheck = $("#checkbox_user_email").is(":checked"); 
	if(isCheck){
		setCookie("userid", $("#user_email").val().trim(), 30);
	}else{
		setCookie("userid", $("#user_email").val().trim(), 0);
	}
	
	// 중복클릭 방지.
	$(".login_button>button").attr("onclick","").unbind("click");
	
	// submit.
	var form = document.getElementById("userLogin");
	form.action = "loginRequest";
	form.submit();	
}