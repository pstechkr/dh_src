<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
	<div id="header_wrap">
		<h1><img src="${pageContext.request.contextPath}/resources/img/common_logo.png" alt="엘루비"><span>엘루비 예약관리 시스템</span></h1>
		<div id="personal_menu">
			<ul>
				<li class="login"><a href="logout"><span>${client_name}</span>님 로그아웃</a></li>
				<li class="mypage"><a href="#none"><span>My Page</span></a></li>
				<li class="dibs"><a href="#none"><span>찜한 상품</span></a></li>
			</ul>
		</div>
	</div>
	<nav id="gnb">
		<ul>
			<li><a href="#none">서비스 공지</a></li>
			<li><a href="store">매장 관리</a></li>
			<li><a href="reservation">예약 및 상품관리</a></li>
			<li><a href="schedule">스케줄 관리</a></li>
			<li><a href="community">고객 관리</a></li>
			<li><a href="#none">결제 관리</a></li>
		</ul>
	</nav>
</header>