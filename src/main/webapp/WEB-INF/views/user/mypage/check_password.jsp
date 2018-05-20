<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="userTemplate">
<tiles:putAttribute name="title">개인정보 수정 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript">$("nav#lnb ul li:nth-child(4)").addClass("active");</script>
<script type="text/javascript">
function checkpassword(){
	// submit.
	var form = document.getElementById("passwordCheck");
	form.action = "modify";
	form.submit();
}
</script>
<section id="find_pw">
	<h1>
		<img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비">
		<span>사랑으로 자라다 <strong>엘루비</strong></span>
	</h1>
	<section class="find_box">
		<h2>잠깐!</h2>
		<div>
			<span>
			개인 정보를 안전하게 보호가기 위하여 비밀번호를 다시 한 번 확인합니다.<br>
			항상 비밀번호는 다른 사람에게 노출되지 않도록 주의하세요.
			</span>
			<div class="input_box">
				<form name="passwordCheck" id="passwordCheck" method="post" onsubmit="return false;">
					<input type="password" id="user_password" name="user_password">
					<button onclick="javascript:checkpassword();">비밀번호 확인</button>
				</form>
			</div>
			<!-- <span class="login"><a href="login">로그인 화면으로</a></span> -->
		</div>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>