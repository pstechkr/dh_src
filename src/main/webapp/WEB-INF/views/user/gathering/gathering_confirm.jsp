<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="gatheringTemplate">
<tiles:putAttribute name="title">엘루비 프로젝트 예약하기 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/gathering_confirm.js"></script>
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
            	<input type="radio" name="reservation_member_check" value="1" checked="checked"><span>구매자 정보와 동일</span><input type="radio" name="reservation_member_check" value="2"><span>새로 입력</span>
        	</div>
            <div class="clear"></div>
        </div>
        <div class="reservation_info dashed">
        	<dl class="reservation_name">
                <dt>이름</dt>
                <dd><input type="text" id="_name" value="${userInfoModel.user_name}"></dd>
        	</dl>
            <dl class="reservation_phoneno">
                <dt>연락처</dt>
                <dd>
                	<input type="text" id="_phoneno1" value="" maxlength="3"> - <input type="text" id="_phoneno2" value="" maxlength="4"> - <input type="text" id="_phoneno3" value="" maxlength="4"> 
               	</dd>
            </dl>
            <dl class="reservation_email">
                <dt>이메일</dt>
                <dd>
                	<input type="text" id="_email" value="${userInfoModel.user_email}">
               	</dd>
            </dl>
        </div>
        
        <h2>예약 상품 정보</h2>
        <div class="reservation_menu dashed">
        	<dl class="reservation_menu_name">
        		<dt>상품명</dt>
        		<dd>${gatheringInfoModel.gathering_name}</dd>
        	</dl>
        	<%-- <dl class="reservation_menu_instruction">
        		<dt>상품 설명</dt>
        		<dd>${gatheringInfoModel.menu_instruction}</dd>
        	</dl> --%>
            <dl class="reservation_date">
                <dt>예약 날짜</dt>
                <dd>${gatheringInfoModel.gathering_ymd}</dd>
            </dl>
            <dl class="reservation_time">
                <dt>예약 시간</dt>
                <dd>${gatheringInfoModel.gathering_start_time} ~ ${gatheringInfoModel.gathering_end_time}</dd>
            </dl>
            <dl class="reservation_member">
                <dt>예약 인원</dt>
                <dd>${member}명</dd>
            </dl>
            <!-- <dl class="reservation_memo">
                <dt>예약메모</dt>
                <dd><textarea row="5"></textarea></dd>
            </dl> -->
        </div>

        <h2>결제 정보 입력</h2>
        <div class="account_info dashed">
        	<dl class="account_select">
            	<dt>결제 수단 선택</dt>
            	<dd>
            		<input type="radio" name="reservation_account_check" value="0"><span>무통장 입금</span>
            		<!-- <input type="radio" name="reservation_account_check" value="1"><span>신용카드 결제</span>
            		<input type="radio" name="reservation_account_check" value="2"><span>핸드폰 결제</span> -->
            	</dd>
            </dl>
            <dl class="coupon">
            	<dt>쿠폰</dt>
            	<dd><select id="_coupon_seq">
           				<option price="${gathering_price}" value="">선택하세요.</option>
            			<c:forEach var="list" items="${couponList}" varStatus="status">
            				<option price="${list.total_price}" value="${list.coupon_id}">${list.coupon_name}</option>
           				</c:forEach>
            		</select>
           		</dd>
            </dl>
        	<div class="total_price">
            	<span>총 결제 금액</span><span class="price">${gathering_price}</span><span>원</span>
            </div>
        </div>
        <section class="agreement">
			<h2>개인정보 제 3자 제공 및 주의 사항 동의</h2>
			<div>
				<p>
개인정보 제3자 제공 및 주의 사항<br><br>
- 개인 정보를 제공받는 자 : ${gatheringInfoModel.gathering_place}<br>
- 개인 정보를 제공받는 자의 개인 정보 이용 목적 : 주문 상품 서비스 제공, 고객 상담 및 불만 처리.<br>
- 제공하는 개인 정보의 항목 : 성명, 주소, 연락처(안심번호 적용 시 연락처는 제외), 이메일 주소.<br>
- 개인 정보를 제공받는 자의 개인 정보 보유 및 이용기간 : 이용 목적 달성 시까지.<br><br>
- 고객님께서는 동의하지 않을 수 있으며, 미동의 시 거래가 제한됩니다.<br>
				</p>
				<input type="checkbox"><label for="">개인정보 제 3자 제공에 동의합니다.</label>
			</div>
		</section>

        
        <div class="confirm btn_box">
        	<button onclick="confirm('${member}');">결제하기</button>
        </div>
   </section>
</section>

</tiles:putAttribute>
</tiles:insertDefinition>