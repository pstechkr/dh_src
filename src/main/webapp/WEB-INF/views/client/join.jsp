<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="clientTemplate">

	<tiles:putAttribute name="body">

	<script type="text/javascript">
	function clientRegisterAction(){
		var form = document.getElementById("clientRegisterInfo");
		form.action = "register";
		form.submit();
	}
	</script>

	<body>
		<h1>사장님 회원가입 페이지</h1>
		<form name="clientRegisterInfo" id="clientRegisterInfo" method="post" enctype="multipart/form-data">
			<p>이메일 : <input type="text" id="client_email" name="client_email" value="" /></p>
			<p>비밀번호 : <input type="text" id="client_password" name="client_password" value="" /></p>
			<p>이름 : <input type="text" id="client_name" name="client_name" value="" /></p>
			<p>사업장 이름 : <input type="text" id="client_companyname" name="client_companyname" value="" /></p>
			<p>연락처 : <input type="text" id="client_contactnumber" name="client_contactnumber" value="" /></p>
			<p>사업자등록번호 : <input type="text" id="client_license" name="client_license" value="" /></p>
		    <p>사업자등록증 첨부사진 : <input type="file" id="client_license_pic_file" name="client_license_pic_file" value="" /></p>
			<p>업태 : <input type="text" id="client_category1" name="client_category1" value="" /></p>
			<p>종목 : <input type="text" id="client_category2" name="client_category2" value="" /></p>
			<p>은행 : <input type="text" id="client_banking" name="client_banking" value="" /></p>
			<p>클라이언트 연락 이메일 : <input type="text" id="client_contactemail" name="client_contactemail" value="" /></p>
		</form>
		<button type="button" id="btnJoin" onclick="javascript:clientRegisterAction();">가입요청</button>
	</body>

	</tiles:putAttribute>
</tiles:insertDefinition>
			
