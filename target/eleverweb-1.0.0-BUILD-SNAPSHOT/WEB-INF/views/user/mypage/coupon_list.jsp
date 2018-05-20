<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="userTemplate">
<tiles:putAttribute name="title">구매 내역 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/popup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/mypage.js"></script>
<script type="text/javascript">$("nav#lnb ul li:nth-child(3)").addClass("active");</script>
<section id="coupon">
	<h2>쿠폰관리</h2>
	<div class="state">장지한님이 사용 가능한 쿠폰은 총 <span>${unused_count}</span>개 입니다.</div>
	<section class="list_table">
		<div class="list_header">
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage/coupon'">전체보기</button>
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage/coupon?q=unused'">사용가능</button>
			<span>* 최근 1년 이내의 내역을 확인할 수 있습니다.</span>
		</div>
		<table summary="예약 및 구매완료된 상품을 확인할 수 있습니다.">
			<caption>예약 상품 리스트</caption>
			<colgroup>
				<col width="300px">
				<col width="200px">
				<col width="200px">
                <col width="100px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">쿠폰명</th>
					<th scope="col">할인액/율</th>
					<th scope="col">유효기간</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach var="list" items="${couponInfoList}" varStatus="status">
				<tr>
					<td>
						<span>${list.coupon_name}</span>
					</td>
					<td>
						<span>${list.coupon_price}</span>
					</td>
					<td>
						<span>${list.coupon_limit}</span><br>
					</td>
					<td>
						<span>${list.use_yn}</span>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			
		</table>
		<div class="pagination">
			<input type="hidden" id="last_page" value="1">
			<button class="btn_prev" onClick="javascript:btnPagination('prev')">prev</button>
				<span class="this_page" onClick="javascript:pagination('1')">1</span>
			<button class="btn_next" onClick="javascript:btnPagination('next')">next</button>
		</div>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>