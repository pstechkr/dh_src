<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="nomenuTemplate">
<tiles:putAttribute name="title">비밀번호 찾기</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript">

function findpassword(){

	// 유효성 검사
	if(!$("#user_email").val().length > 0){alert("이메일을 입력해 주세요.");return false;}
	
	// success 뜰 때까지 중복클릭 방지.
	$(".input_box>button").attr("onclick","").unbind("click");
	
	var request = {};
	request["user_email"] = $("#user_email").val();

	$.ajax({
		url: "passwordRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
	    	
	    	alert(response["msg"]);
	    	$(".input_box>button").attr("onclick","javascript:findpassword();").bind("click");
		} // success end
	}); // ajax end
}

</script>

<nav id="lnb">
	<h2>비밀번호 찾기</h2>
</nav>
<section id="find_pw">
	<h1>
		<img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비">
		<span>사랑으로 자라다 <strong>엘루비</strong></span>
	</h1>
	<section class="find_box">
		<h2>비밀번호 찾기</h2>
		<div>
			<span>
			가입시 아이디로 등록한 메일 주소를 입력해주세요.<br>
			해당 메일 주소로 임시 비밀번호를 보내드립니다.
			</span>
			<div class="input_box">
				<input type="text" id="user_email" name="user_email">
				<button onclick="javascript:findpassword();">임시비밀번호 발송</button>
			</div>
			<span class="login"><a href="login">로그인 화면으로</a></span>
		</div>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>