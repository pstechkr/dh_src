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
	
	// 성별 라디오 버튼 클릭 이벤트.
	$("input:radio[name='gender']").on({
		click: function(event){
			$("#user_gender").val($(this).val());
		}
	});
	
	// 이메일 checked된 상태에서 변경하고 싶을 때.
	$("#user_email").on({
		click: function(event){
			if($(this).attr("readonly") == "readonly"){
				if(!confirm("새로운 이메일을 입력하시겠습니까?")){return false;}
				$(this).removeAttr("readonly");
				$("button.btn_email_check").attr("onclick","javascript:email_check();").bind("click");
			}
		}
	});
	
});

//이메일 체크
function email_check(){

	var request = {};
	requestUrl = "email_check";
	
	if($("#user_email").val() == ""){alert("이메일을 확인해 주세요.");return false;};
	var email = $("#user_email").val();
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(regex.test(email) === false){
		alert("잘못된 이메일 형식 입니다!");
		return false;
	}

	request["user_email"] = $("#user_email").val();
	$.ajax({
		url: requestUrl,
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
       	success:function(response){

       		// 1: 중복인 경우 / 0: 중복이 아닌 경우
       		if(Number(response["isCheck"])){
       			alert("이미 회원으로 등록되어 있는 이메일 주소입니다.");
       		}else{
       			alert("해당 이메일을 사용할 수 있습니다.");
       			$("#user_email_check").val("1");
       			$("#user_email").attr("readonly", true);
       			// 중복클릭 방지.
       			$("button.btn_email_check").attr("onclick","").unbind("click");
       		}
		}
	});	
}

// 회원가입
function register(){
	
	// 유효성 체크
	if($("#user_email").val() == ""){alert("이메일을 확인해 주세요.");return false;};
	if($("#user_email").attr("readonly") != "readonly"){alert("이메일 중복 확인이 필요합니다.");return false;};
	if($("#user_password").val() == ""){alert("비밀번호를 확인해 주세요.");return false;};
	if($("#user_password").val().length < 6){alert("비밀번호의 길이는 6글자 이상이어야 합니다.");return false;};
	if($("#user_repassword").val() == ""){alert("비밀번호를 확인해 주세요.");return false;};
	if($("#user_password").val() != $("#user_repassword").val()){alert("비밀번호가 동일하지 않습니다.");return false;};
	if($("#user_name").val() == ""){alert("이름을 확인해 주세요.");return false;};
	if($("#gender").val() == ""){alert("성별을 확인해 주세요.");return false;};
	if($("#user_birthday_month").val() == ""){alert("생년월일을 확인해 주세요.");return false;};
	if($("#user_phoneno1").val() == ""){alert("연락처를 확인해 주세요.");return false;};
	if($("#user_phoneno2").val() == ""){alert("연락처를 확인해 주세요.");return false;};
	if($("#user_phoneno3").val() == ""){alert("연락처를 확인해 주세요.");return false;};
	var num_check = /^[0-9]+$/;
	if(!num_check.test($("#user_phoneno1").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	if(!num_check.test($("#user_phoneno2").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	if(!num_check.test($("#user_phoneno3").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	
	if($("#chk_1").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}
	if($("#chk_2").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}
	
	// 데이터 넣기 (핸드폰)
	$("#user_phoneno").val($("#user_phoneno1").val() + "-" + $("#user_phoneno2").val() + "-" + $("#user_phoneno3").val());
	
	// 중복클릭 방지.
	$("button.join_submit").attr("onclick","").unbind("click");
	
	var form = document.getElementById("userRegisterInfo");
	form.action = "register";
	form.submit();
}

function emailClick(){
	
	if($("#chk_1").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}
	if($("#chk_2").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}
	
	$(".hidden").slideDown();
	$(".hidden").animatescroll({padding:20});
	
}

function joinfacebooklogin() {  
	
	if($("#chk_1").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}
	if($("#chk_2").is(":checked") == false){alert("이용약관 및 개인정보 처리방침에 동의해야 합니다.");return false;}

	facebooklogin();
	
}