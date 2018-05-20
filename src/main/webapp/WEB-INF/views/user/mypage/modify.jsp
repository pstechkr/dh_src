<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="userTemplate">
<tiles:putAttribute name="title">정보 수정 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/modify.js"></script>
<script type="text/javascript">$("nav#lnb ul li:nth-child(4)").addClass("active");</script>
<section id="modify">
	<h2>회원 정보</h2>
	<div class="contents">
		<dl class="modify_id">
			<dt>아이디</dt>
			<dd>${model.user_email}</dd>
		</dl>
		
		<form name="userModifyInfo" id="userModifyInfo" method="post" onsubmit="return false;">
		
			<dl class="modify_pw">
				<dt>비밀번호</dt>
				<dd><input type="password" id="user_password" name="user_password" maxlength="14"></dd>
			</dl>
			<dl class="modify_pw2">
				<dt>비밀번호 확인</dt>
				<dd><input type="password" id="user_repassword" name="user_repassword" maxlength="14"></dd>
			</dl>
			<dl class="modify_name">
				<dt>성명</dt>
				<dd><input type="text" id="user_name" name="user_name" maxlength="10" value="${model.user_name}" valid></dd>
			</dl>
			<dl class="modify_gender">
				<dt>성별</dt>
				<dd><label>${gender}</label></dd>
			</dl>
			<dl class="modify_birthday">
				<dt>생년월일</dt>
				<dd>
					<input type="text" id="user_birthday_year" name="user_birthday_year" value="${birthday[0]}" readonly="readonly">년
					<input type="text" id="user_birthday_month" name="user_birthday_month" value="${birthday[1]}" readonly="readonly">월
					<input type="text" id="user_birthday_day" name="user_birthday_day" value="${birthday[2]}" readonly="readonly">일
					<input type="hidden" id="user_birthday" name="user_birthday" value="${model.user_birthday}" vaild>
					<input type=hidden id="datepicker"/>
				</dd>
			</dl>
			<dl class="modify_phone">
				<dt>연락처</dt>
				<dd><input type="text" id="user_phoneno1" name="user_phoneno1" value="${phoneno[0]}" maxlength="3">-<input type="text" id="user_phoneno2" name="user_phoneno2" value="${phoneno[1]}" maxlength="4">-<input type="text" id="user_phoneno3" name="user_phoneno3" value="${phoneno[2]}" maxlength="4">
					<input type="hidden" id="user_phoneno" name="user_phoneno" value="${model.user_phoneno}">
				</dd>
			</dl>
		</form>
		<button class="btn_modify" onclick="javascript:modify();">수정</button>
	</div>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>