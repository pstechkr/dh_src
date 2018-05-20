<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   		<title><tiles:insertAttribute name="title" ignore="true" /></title>
   		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
   		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/default.js"></script>
   		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/default.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/client.css">
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>