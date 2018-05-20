$(document).ready(function() {
	
	// 데이트피커 실행 
	$("#datepicker").datepicker({
		dateFormat: 'yymmdd',
    	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	dayNamesMin: ['일','월','화','수','목','금','토'],
    	changeMonth: true,
    	yearRange: "c-100:c",
    	changeYear: true,
    	showMonthAfterYear: true,
    	showOn: "button",
    	showAnim: "slideDown"
	});
	
	// 데이트 피커 버튼에 글씨 넣기
	$(".ui-datepicker-trigger").html("날짜선택");

	// 버튼 피커 눌렀을 때 이벤트.
	$("#datepicker").on({
		change: function (event){			
			var date = $(this).val(); 
			$("#user_birthday_year").val(date.substring(0,4));
			$("#user_birthday_month").val(date.substring(4,6));
			$("#user_birthday_day").val(date.substring(6,8));
			$("#user_birthday").val(date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8));
		}
	});
});

function modify(){
	
	// 비밀번호 유효성 검사
	if($("#user_password").val() != "" || $("#user_repassword").val() != ""){
		if($("#user_password").val() != $("#user_repassword").val()){
			alert("비밀번호가 서로 일치하지 않습니다.");
			return false;
		}
	}
	
	var form = document.getElementById("userModifyInfo");
	// value null 검사
	if(!validNullCheck(form)){
		alert("입력 정보를 다시 확인해 주세요.");
		return false;
	}
	
	
	// 휴대폰번호
	if($("#user_phoneno1").val() == "" || $("#user_phoneno2").val() == "" || $("#user_phoneno3").val() == ""){
		if($("#user_phoneno1").val() == "" && $("#user_phoneno2").val() == "" && $("#user_phoneno3").val() == ""){
		}else{alert("연락처를 다시 확인해 주세요.");return false;}
	}else{
		var num_check = /^[0-9]+$/;
		if(!num_check.test($("#user_phoneno1").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
		if(!num_check.test($("#user_phoneno2").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
		if(!num_check.test($("#user_phoneno3").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
		$("#user_phoneno").val($("#user_phoneno1").val() + "-" + $("#user_phoneno2").val() + "-" + $("#user_phoneno3").val());
	}
	
	$("button.btn_modify").attr("onclick","").unbind("click");
	form.action = "modifyRequest";
	form.submit();
	
}

function validNullCheck(form){
	
	for(var i=0;i<form.elements.length;i++){
		var obj = form.elements[i];
		if(obj.getAttribute("valid") != null){
			
			if(obj.value == ""){
				console.log("value 없음");
				return false;
			}
		}
	}
	return true;
}
