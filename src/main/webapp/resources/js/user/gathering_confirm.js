/* 
 * 유저 쪽 Gathering 관련 js파일.
 * Copyright(c) Jihan Jang. (TIEIN Communications) 
 */
$(document).ready(function(){

	// 처음
	var tmp_phoneno = $(".customer_info>.user_phoneno>dd").text();
	var user_phoneno = tmp_phoneno.split("-");
	$("#_phoneno1").val(user_phoneno[0]);
	$("#_phoneno2").val(user_phoneno[1]);
	$("#_phoneno3").val(user_phoneno[2]);
	
	
	// 예약 방법 radio 버튼 선택시
	$("input[name='reservation_account_check']").on({
		
		click: function(event){
			// 무통장
			if($(this).val() == "0"){

				if($(".wire_transfer").length == "0"){
				var wire_transfer = "<div class='account_method'>"
										+ "<dl class='wire_transfer'>"
											+ "<dt>무통장 입금 안내</dt>"
											+ "<dd>국민은행 810101-04-238339 (장지한)</dd>"
										+"</dl>"
										+ "<dl class='wire_transfer'>"
											+ "<dt>현금영수증 발급신청 안내</dt>"
											+ "<dd>현재 결제모듈 구축중에 있어 현금영수증 발급에 필요한 정보는 모임 현장에서 받습니다.<br>양해 부탁드립니다.</dd>"
										+"</dl>"
									+"</div>";
				$(".account_info>.account_select").after(wire_transfer);
				}
			}
		}
	});
	
	$("input[name='reservation_member_check']").on({
		
		click: function(event){
			if($(this).val() == "1"){
				
				var user_name = $(".customer_info>.user_name>dd").text();
				var tmp_phoneno = $(".customer_info>.user_phoneno>dd").text();
				var user_phoneno = tmp_phoneno.split("-");
				var user_email = $(".customer_info>.user_email>dd").text();
				
//				$("#_name").attr("readonly", "readonly");
				$("#_name").val(user_name);
				
//				$("#_phoneno1").attr("readonly", "readonly");
//				$("#_phoneno2").attr("readonly", "readonly");
//				$("#_phoneno3").attr("readonly", "readonly");
				$("#_phoneno1").val(user_phoneno[0]);
				$("#_phoneno2").val(user_phoneno[1]);
				$("#_phoneno3").val(user_phoneno[2]);
				
//				$("#_email").attr("readonly", "readonly");
				$("#_email").val(user_email);
				
			}else if($(this).val() == "2"){
				
//				$("#_name").removeAttr("readonly");
				$("#_name").val("");
				
//				$("#_phoneno1").removeAttr("readonly");
//				$("#_phoneno2").removeAttr("readonly");
//				$("#_phoneno3").removeAttr("readonly");
				$("#_phoneno1").val("");
				$("#_phoneno2").val("");
				$("#_phoneno3").val("");
				
//				$("#_email").removeAttr("readonly");
				$("#_email").val("");
			}
		}
	});
	
	$(".coupon>dd>select").on({
		
		change : function(event){
			couponRequest($(".coupon>dd>select option:selected").attr("price"));
		}
	});
});

// 결제하기 버튼 클릭
function confirm(_member){
	
	//유효성 검사
	if($("#_name").val() == ""){alert("예약하시는 분의 이름을 확인해 주세요.");return false;};
	if($("#_phoneno1").val() == ""){alert("예약하시는 분의 연락처를 확인해 주세요.");return false;};
	if($("#_phoneno2").val() == ""){alert("예약하시는 분의 연락처를 확인해 주세요.");return false;};
	if($("#_phoneno3").val() == ""){alert("예약하시는 분의 연락처를 확인해 주세요.");return false;};
	var num_check = /^[0-9]+$/;
	if(!num_check.test($("#_phoneno1").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	if(!num_check.test($("#_phoneno2").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	if(!num_check.test($("#_phoneno3").val())){alert("연락처에는 숫자만 입력할 수 있습니다!");return false;}
	if($("#_email").val() == ""){alert("예약하시는 분의 이메일 주소를 확인해 주세요.");return false;};
	if($("#_email").val() == ""){alert("예약하시는 분의 이메일 주소를 확인해 주세요.");return false;};
	if($("section.agreement input[type='checkbox']").is(":checked") == false){alert("개인 정보 제3자 제공에 동의해야 합니다.");return false;}
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(!regex.test($("#_email").val())){alert("잘못된 이메일 형식 입니다!");return false;}
	if($("input:radio[name='reservation_account_check']:checked").val() == null){alert("결제 수단을 확인해 주세요.");return false;};
	
	
	// form 생성
	var $form = $("<form></form>");
	$form.attr("action", "confirmCommit");
	$form.attr("method", "post");
	$form.appendTo("body");
	
	var _user_name = $("#_name").val();
	var _user_phoneno = $("#_phoneno1").val() + "-" + $("#_phoneno2").val() + "-" + $("#_phoneno3").val();
	var _user_email = $("#_email").val();
	var _account_way = $("input:radio[name='reservation_account_check']:checked").val();
	var _coupon_seq = $("#_coupon_seq").val();
	
	var user_name = $("<input type='hidden' name='user_name' value='" + _user_name + "'>");
	var user_phoneno = $("<input type='hidden' name='user_phoneno' value='" + _user_phoneno + "'>");
	var user_email = $("<input type='hidden' name='user_email' value='" + _user_email + "'>");
	var member = $("<input type='hidden' name='member' value='" + _member + "'>");
	var account_way = $("<input type='hidden' name='account_way' value='" + _account_way + "'>");

	var coupon_seq = $("<input type='hidden' name='coupon_seq' value='" + _coupon_seq + "'>");
	
	// 중복클릭 방지.
	$(".confirm>button").attr("onclick","").unbind("click");
	
	$form.append(user_name).append(user_phoneno).append(user_email).append(member).append(account_way).append(coupon_seq);
	$form.submit();
};

function addcomma(num) {
    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
function uncomma(num) {
    num = String(num);
    return num.replace(/[^\d]+/g, '');
}

// 쿠폰 선택에 따른 가격 변화 확인
function couponRequest(coupon){
	
	$(".total_price>.price").text(coupon);

}
