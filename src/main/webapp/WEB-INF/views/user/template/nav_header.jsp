<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<div id="header_wrap">
		<h1><a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비"><span>사랑으로 자라다 <strong>엘루비</strong></span></a></h1>
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
<section class="filter_wrap">
	<div class="filter_header">
		<c:choose>
		<c:when test="${model.store_localname1 != null}">
			<a onclick="javascript:filter();">${model.store_localname1} ${model.store_localname2} ${model.store_name}</a>
		</c:when>
		<c:otherwise>
			<a onclick="javascript:filter();">지역 선택</a>
		</c:otherwise>
		</c:choose>
	</div>
	<div class="filter_menu">
		<ul class="filter_column1">
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
			<li><a href="#">첫 번째 지역필터</a></li>
		</ul>
		<ul class="filter_column2">
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
			<li><a href="#">두 번째 지역필터</a></li>
		</ul>
		<ul class="filter_column3">
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
			<li><a href="#">세 번재 지역필터</a></li>
		</ul>
	</div>
</section>
<!-- <section class="item_place">
	<span>필터1</span>&gt;<span>필터2</span>&gt;<span>필터3</span>
</section> -->