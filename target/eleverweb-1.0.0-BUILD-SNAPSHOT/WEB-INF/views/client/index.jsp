<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="clientTemplate">
	<tiles:putAttribute name="title">사장님 로그인 페이지</tiles:putAttribute>

	<tiles:putAttribute name="body">	
		<script type="text/javascript">
		function clientLogin(){
			var form = document.getElementById("clientLoginInfo");
			form.action = "login";
			form.submit();
		}
		
		function clientLogin_2(){
			var form = document.getElementById("clientLoginInfo");
			form.action = document.location.href = 'login';
			form.submit();
		}
		</script>
	
		<body>
			<h1>사장님 로그인 페이지</h1>
			<h2>${client_name}님 접속중! client_seq = ${client_seq}</h2>
			<form name="clientLoginInfo" id="clientLoginInfo" method="post">
				<p>email : <input type="text" id="client_email" name="client_email" value="" /></p>
				<p>password : <input type="text" id="client_password" name="client_password" value="" /></p>
			</form>
			<button type="button" onclick="clientLogin_2()">로그인</button>
			<a href="join"><button type="button">가입하기</button></a>
		</body>
	</tiles:putAttribute>

</tiles:insertDefinition>
			
