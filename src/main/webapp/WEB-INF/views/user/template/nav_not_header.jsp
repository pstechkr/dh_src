<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div id="header_wrap">
		<h1><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비"><span>사랑으로 자라다 <strong>엘루비</strong><span class="version">Alpha</span></span></a></h1>
		<div id="personal_menu">
			<ul>
			
				<c:choose>
					<c:when test="${user_name == null}">
						<li class="join"><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
						<li class="login"><a href="${pageContext.request.contextPath}/login">로그인</a></li>
					</c:when>
					<c:otherwise>
					<li class="login"><a href="${pageContext.request.contextPath}/logout"><span>${user_name}</span>님 로그아웃</a></li>
					<li class="mypage"><a href="${pageContext.request.contextPath}/mypage/ticket"><span>My Page</span></a></li>
					<!-- <li class="dibs"><a href="#none">찜한 상품</a></li> -->
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</header>