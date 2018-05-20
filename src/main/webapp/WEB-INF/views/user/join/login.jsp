<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="nomenuTemplate">
<tiles:putAttribute name="title">로그인 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/idCookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/facebook.js"></script>
<nav id="lnb">
	<h2>로그인</h2>
</nav>
<section id="login">
	<h1>
		<img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비">
		<span>사랑으로 자라다 <strong>엘루비</strong></span>
	</h1>
	<section class="login_box">
			<h2>로그인</h2>
			<div class="login_wrap">
			
				<form name="userLogin" id="userLogin" method="post" onsubmit="return false;">
					<div class="login_input">
						<dl>
							<dt><label for="">아이디</label></dt>
							<dd><input type="text" id="user_email" name="user_email"></dd>
						</dl>
						<dl>
							<dt><label for="">비밀번호</label></dt>
							<dd><input type="password" id="user_password" name="user_password"></dd>
						</dl>
					</div>
				</form>
				
				<div class="login_button">
					<button class="general" onclick="javascript:login();">로그인</button>
					<button class="facebook" onclick="javascript:facebooklogin();">Facebook 로그인</button>
				</div>
				<div class="login_footer">
					<div class="checkbox">
						<input id="checkbox_user_email" name="checkbox_user_email" type="checkbox"><label for="">아이디 저장</label>
					</div>
					<div class="find">
						<span><a href="find_password">비밀번호찾기</a></span>
					</div>
				</div>
			</div>
			<div class="join_wrap">
				<dl>
					<dt>아직 엘루비 회원이 아니신가요?</dt>
					<dd>
						<a href="join"><button>엘루비 회원 가입하기</button></a>
					</dd>
				</dl>
			</div>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>