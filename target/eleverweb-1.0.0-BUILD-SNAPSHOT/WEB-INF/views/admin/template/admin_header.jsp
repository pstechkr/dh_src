<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header>
	<div id="header_wrap">
		<h1><img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비"><span>엘루비 운영 시스템</span></h1>
		<div id="personal_menu">
			<ul>
				<li class="login"><a href="${pageContext.request.contextPath}/admin/unAuth"><span>관리자</span>님 로그아웃</a></li>
				<!-- <li class="mypage"><a href="#none"><span>My Page</span></a></li>
				<li class="dibs"><a href="#none"><span>찜한 상품</span></a></li> -->
			</ul>
		</div>
	</div>
	<nav id="gnb">
		<ul>
			<%-- <li><a href="${pageContext.request.contextPath}/admin/notice">매장 공지사항</a></li> --%>
			<li><a href="${pageContext.request.contextPath}/admin/slider">슬라이더 관리</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/community/review/write">리뷰 쓰기</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/coupon/list">쿠폰발행 관리</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/user/list">일반이용자 리스트</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/gathering/list">기획전 관리</a></li>
		</ul>
	</nav>
</header>