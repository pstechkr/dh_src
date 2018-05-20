<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="userTemplate">
<tiles:putAttribute name="title">예매 내역 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/popup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/mypage.js"></script>

<script type="text/javascript">$("nav#lnb ul li:nth-child(1)").addClass("active");</script>
<section id="mypage">
	<h2>예매내역</h2>
	<div class="state">${user_name}님이 곧 만나볼 데이트가 <span>${total_count}</span>건 있습니다.</div>
	<section class="list_table">
		<div class="list_header">
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage/ticket'">전체</button>
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage/ticket?product=gathering'">엘루비 프로젝트</button>
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage/ticket?product=store'">모..G?</button>
			<span>* 웹에서의 결제 취소는 예약 당일 하루 전까지 가능합니다. </span>
		</div>
		<table summary="예약 및 구매완료된 상품을 확인할 수 있습니다.">
			<caption>예약 상품 리스트</caption>
			<colgroup>
				<col width="180px">
				<col width="180px">
				<col width="200px">
				<col width="120px">
				<col width="120px">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">주문 일자 / 주문 번호</th>
					<th scope="col">진행상태</th>
					<th scope="col" class="item_info">상품 정보</th>
					<th scope="col">위치</th>
					<th scope="col"><span>상태 및 후기</span></th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach var="list" items="${ticketInfoList}" varStatus="status">
				<tr class="${list.schedule_seq} ${list.product}">
					<td>
						<span>${list.reg_cdd} ${list.reg_cdt}</span>
						<span class="order_num">${list.order_no}</span>
					</td>
					<td>
						<span>
							${list.status}<br>
							${list.account_way}
						</span>
					</td>
					<td class="item_info">
						<span>${list.product_name}</span><br>
						<span>${list.name}</span><br>
						<span>예약인원 : ${list.member}명</span><br>
						<span>${list.ymd} / ${list.start_time} ~ ${list.end_time}</span><br>
						<span><em>${list.price}원</em></span>
					</td>
					<td class="location">
						<button onclick="javascript:mapView('${list.latitude}','${list.longitude}');">지도보기</button>
					</td>
					<td class="review">
						<c:if test="${list.status == '결제대기'}">
							<button class="rsv_cancel" onclick="javascript:rsvCancelRequest('${list.schedule_seq}', '${list.product}')">예약 취소</button>
						</c:if>
						<c:if test="${list.status == '결제완료'}">
							<c:if test="${list.account_way == '무통장입금'}">
								<button>결제 취소</button>
								<button onclick="javascript:receipt('cash');">영수증 출력</button>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
			<input type="hidden" id="last_page" value="${last_page}">
			<button class="btn_prev" onclick="javascript:btnPagination('prev')">prev</button>
			<c:forEach var="pageList" items="${pagination}" varStatus="status">
				<c:choose>
					<c:when test="${pageList == this_page}">
						<span class="this_page" onclick="javascript:pagination('${pageList}')">${pageList}</span>
					</c:when>
					<c:otherwise>
						<span onclick="javascript:pagination('${pageList}')">${pageList}</span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<button class="btn_next" onclick="javascript:btnPagination('next')">next</button>
		</div>
	</section>
</section>
<div id="mask">
	<div id="layer">
		<section class="static_map inbox">
			<div class="map_wrap">
				<h2>지도보기</h2>
				<div id="map"></div>
				<div id="msg"><span>지도를 클릭하면 더욱 자세한 정보를 확인할 수 있습니다.</span></div>
			
			</div>
		</section>
	</div>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>