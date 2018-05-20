<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="storeTemplate">
<tiles:putAttribute name="title">엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<!-- reservation confirm -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/reservation_confirm.js"></script>
<script type="text/javascript">$("nav.item_nav ul li:nth-child(3)>a").addClass("active");</script>

<section id="store_reservation">
	<section class="contents confirm_section">		
        <h2>구매자 정보</h2>
       	<div class="customer_info dashed">
            <dl class="user_name">
                <dt>이름</dt>
                <dd>${userInfoModel.user_name}</dd>
            </dl>
            <dl class="user_phoneno">
                <dt>연락처</dt>
                <dd>${userInfoModel.user_phoneno}</dd>
	        </dl>
			<dl class="user_email">
        		<dt>이메일</dt>
            	<dd>${userInfoModel.user_email}</dd>
        	</dl>
        </div>
        
        <div class="reservation_header">
        	<h2>예약자 정보</h2>
            <div>
            	<input type="radio" name="reservation_member_check" checked="checked"><span>구매자 정보와 동일</span><input type="radio" name="reservation_member_check"><span>새로 입력</span>
        	</div>
            <div class="clear"></div>
        </div>
        <div class="reservation_info dashed">
        	<dl class="reservation_name">
                <dt>이름</dt>
                <dd><input type="text" value="${userInfoModel.user_name}"></dd>
        	</dl>
            <dl class="reservation_phoneno">
                <dt>연락처</dt>
                <dd><input type="text" value="${userInfoModel.user_phoneno}"></dd>
            </dl>
            <dl class="reservation_email">
                <dt>연락처</dt>
                <dd><input type="text" value="${userInfoModel.user_email}"></dd>
            </dl>
        </div>
        
        <h2>상품 정보</h2>
        <div class="reservation_menu dashed">
        	<dl class="reservation_menu_name">
        		<dt>상품명</dt>
        		<dd>${scheduleInfoModel.menu_name}</dd>
        	</dl>
        	<dl class="reservation_menu_instruction">
        		<dt>상품 설명</dt>
        		<dd>${scheduleInfoModel.menu_instruction}</dd>
        	</dl>
            <dl class="reservation_date">
                <dt>예약 날짜</dt>
                <dd>${scheduleInfoModel.schedule_year}년 ${scheduleInfoModel.schedule_month}월 ${scheduleInfoModel.schedule_date}일</dd>
            </dl>
            <dl class="reservation_time">
                <dt>예약 시간</dt>
                <dd>${scheduleInfoModel.schedule_hm} ~ ${scheduleInfoModel.end_time}</dd>
            </dl>
            <dl class="reservation_member">
                <dt>예약 인원</dt>
                <dd>${scheduleInfoModel.member}명</dd>
            </dl>
            <dl class="reservation_memo">
                <dt>예약메모</dt>
                <dd><textarea row="5"></textarea></dd>
            </dl>
        </div>

        <h2>결제 정보 입력</h2>
        <div class="account_info">
        	<dl class="account_select">
            	<dt>결제 수단 선택</dt>
            	<dd><input type="radio" name="reservation_account_check"><span>무통장 입금</span><input type="radio" name="reservation_account_check"><span>신용카드 결제</span><input type="radio" name="reservation_account_check"><span>핸드폰 결제</span></dd>	
            </dl>
        	<div class="total_price">
            	<span>총 결제 금액</span><span class="price">${scheduleInfoModel.menu_price}</span><span>원</span>
            </div>
        </div>
        
        <h2>개인정보 제 3자 제공 및 주의 사항 동의</h2>
        <div class="agree">
        	<textarea></textarea>
            <input type="checkbox"><span>엘루비 이용약관에 동의합니다.</span>
			<textarea></textarea>
            <input type="checkbox"><span>개인정보 처리방침에 동의합니다.</span>
        </div>
        
        <div class="confirm btn_box">
        	
        	<button onclick="confirm(${scheduleInfoModel.schedule_seq});">결제하기</button>
        </div>
   </section>
</section>

</tiles:putAttribute>
</tiles:insertDefinition>