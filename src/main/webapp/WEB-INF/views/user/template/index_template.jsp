<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
    <head>
    	<tiles:insertAttribute name="general_head" />
    	<tiles:insertAttribute name="head" />
    	<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/easySlider1.7.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/index.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){	
				$("#slider").easySlider({
					auto: true,
					speed: 1000,
					pause: 4000,
					continuous: true
				});
				
				$(".item_nav>ul>li>a").eq(0).addClass("active");
			});
		</script>
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>
