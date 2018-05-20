<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="clientTemplate">
<tiles:putAttribute name="title">스케줄 관리</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript">
$(document).ready(function(){
	calendarRequest("ready");
});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/reservation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/client/schedule.js"></script>
<div id="contents">
	<section id="schdl_management">
		<div class="calendar_wrap">
			<section id="calendar">
				<h2>날짜 선택</h2>
				<div class="table_layout">
					<div class="cal_header">
						<button class="prev_mon" onclick="calendarRequest('prev');">이전 달</button>
						<div class="date_area">
							<span id="calYear">0000</span>.<span id="calMonth">00</span>
						</div>
						<button class="next_mon" onclick="javascript:calendarRequest('next');">다음 달</button>
					</div>
					<table summary="예약 현황을 확인할 수 있습니다.">
						<caption>예약 현황 테이블</caption>
						<thead>
							<tr>
								<th scope="col">일</th>
								<th scope="col">월</th>
								<th scope="col">화</th>
								<th scope="col">수</th>
								<th scope="col">목</th>
								<th scope="col">금</th>
								<th scope="col">토</th>
							</tr>
						</thead>
						<tbody class="calendar_load">
						</tbody>
					</table>
				</div>
			</section>
			<section id="rsv_list">
				<h2>시간별 예약자 명단</h2>
				<table summary="시간별로 예약자를 확인하실 수 있습니다.">
					<caption>시간별 예약자 명단</caption>
					<thead>
						<tr>
							<th scope="col">시간</th>
							<th scope="col">방문 예정 / 예약 진행</th>
						</tr>
					</thead>
					<tbody id="timetable">
						<tr>
							<td><span>00:00</span></td>
							<td><span>0</span>명 / <span>0</span>명</td>
						</tr>
					</tbody>
				</table>
			</section>
		</div>
		<section id="visit_list" class="rsv_list">
			<h2>방문 예정 명단</h2>
			<table summary="방문 예정 명단을 확인하실 수 있습니다.">
				<caption>방문 예정 명단</caption>
				<colgroup>
					<col width="25px">
					<col width="130px">
					<col width="295px">
					<col width="90px">
					<col width="112px">
					<col width="166px">
					<col width="82px">
				</colgroup>
				<thead>
					<tr>
						<th colspan="2" scope="col"><span>예약시간</span></th>
						<th scope="col"><span>상품명</span></th>
						<th scope="col"><span>예약자</span></th>
						<th scope="col"><span>예약 인원</span></th>
						<th scope="col"><span>연락처</span></th>
						<th scope="col"><span>상태</span></th>
					</tr>
				</thead>
				<tbody id="readyList">
					<tr>
						<th scope="row"><button>이 아이템을 제거합니다.</button></th>
						<td><span>11:00 ~ 12:00</span></td>
						<td><span>엘루비 커플 세트 A형 (머그컵)</span></td>
						<td><span>이름</span></td>
						<td><span>2명</span></td>
						<td><span>010-1234-5678</span></td>
						<td><span>결제 완료</span></td>
					</tr>
					<tr class="edit_item">
						<th scope="row"><button>이 아이템을 제거합니다.</button></th>
						<td><span>11:00 ~ 12:00</span></td>
						<td>
							<select class="item_name" name="" id="">
								<option value="">아이템 테스트</option>	
							</select>
						</td>
						<td>
							<input type="text" size="10" class="user_name">
						</td>
						<td>
							<select name="" id="" class="user_number">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</select>
						</td>
						<td>
							<input type="text" class="user_tel">
						</td>
						<td>
							<input type="text" class="state">
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<button class="add_button">직접 예약자 명단 추가하기</button>
			</div>
		</section>
		<section id="proceed_list" class="rsv_list">
			<h2>예약 진행 명단</h2>
			<table summary="예약 진행 명단을 확인하실 수 있습니다.">
				<caption>예약 진행 명단</caption>
				<colgroup>
					<col width="25px">
					<col width="130px">
					<col width="295px">
					<col width="90px">
					<col width="112px">
					<col width="166px">
					<col width="82px">
				</colgroup>
				<thead>
					<tr>
						<th colspan="2" scope="col"><span>예약시간</span></th>
						<th scope="col"><span>상품명</span></th>
						<th scope="col"><span>예약자</span></th>
						<th scope="col"><span>예약 인원</span></th>
						<th scope="col"><span>연락처</span></th>
						<th scope="col"><span>상태</span></th>
					</tr>
				</thead>
				<tbody id="doingList">
					<tr>
						<th scope="row"><button>이 아이템을 제거합니다.</button></th>
						<td><span>11:00 ~ 12:00</span></td>
						<td><span>엘루비 커플 세트 A형 (머그컵)</span></td>
						<td><span>이름</span></td>
						<td><span>2명</span></td>
						<td><span>010-1234-5678</span></td>
						<td><span>결제 완료</span></td>
					</tr>
				</tbody>
			</table>
		</section>
	</section>
	<div id="temp"></div>
</div>


</tiles:putAttribute>
</tiles:insertDefinition>