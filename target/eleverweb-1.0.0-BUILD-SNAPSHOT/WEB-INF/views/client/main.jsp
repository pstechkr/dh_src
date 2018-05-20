<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="clientTemplate">
	<tiles:putAttribute name="title">사장님 메인 페이지</tiles:putAttribute>

	<tiles:putAttribute name="body">	

		<script type="text/javascript">
		function clientLogout(){
			form.action = "login";
			form.submit();
		}
		</script>
	
		<body>
			<h1>${client_name}님 접속중! client_seq = ${client_seq}</h1>
			<a href="logout"><button type="button">로그아웃</button></a>
		</body>
	</tiles:putAttribute>

</tiles:insertDefinition>
			
