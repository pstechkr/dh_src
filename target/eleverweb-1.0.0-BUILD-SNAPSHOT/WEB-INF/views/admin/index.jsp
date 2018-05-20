<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="title">관리자 로그인 페이지</tiles:putAttribute>
	<tiles:putAttribute name="body">	
			<h1>관리자 페이지</h1>
			<h2>${admin_name}님 접속중! client_seq = ${admin_seq}</h2>
	</tiles:putAttribute>

</tiles:insertDefinition>
			
