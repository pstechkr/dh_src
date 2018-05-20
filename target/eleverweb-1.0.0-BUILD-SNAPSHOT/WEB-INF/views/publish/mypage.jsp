<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="userTemplate">
<tiles:putAttribute name="title">마이 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript">

function pagination(num){
	
	url = location.href;

	// 현재 주소에서 ? 없다면,
	if(url.indexOf("?") == -1){
		location.href = url + "?p=" + num;
	}else{
		// ?가 있는 경우
		// 페이지 parameter 없는 경우 
		if(url.indexOf("p=") == -1){
			location.href = url + "&p=" + num;
		}else{
			// 페이지 parameter 있는 경우
			var split = url.split("p=");
			location.href = split[0] + "p=" + num;
		}
	}
}

function btnPagination(key){
	
	var this_page = $(".pagination .this_page").text();
	
	if(key == "prev"){
		var prev_page = Number(this_page) - 1;
		
		if(prev_page <= 0){
			pagination("1");
		}else{
			pagination(prev_page);	
		}

	}else if(key == "next"){
		var next_page = Number(this_page) + 1;
		var last_page = ${last_page};
		
		if(next_page >= last_page){
			pagination(last_page);
		}else{
			pagination(next_page);	
		}
	}
}

function rsvCancelRequest(num){
	
	if(!confirm("정말 예약을 취소 하시겠습니까?")){
		return false;
	}
	
	var request = {};
	request["schedule_seq"] = num;
	
	$.ajax({
		url: "rsvCancelRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
			if(response["isCheck"]){
				alert("예약이 취소 되었습니다.");
			}else{
				alert("예약 취소를 실패하였습니다.");
			}
	    	
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end
}



</script>



<section id="mypage">
	<h2>구매 내역</h2>
	<div class="state">${user_name}님의 현재 진행중인 예약 상품은 <span>${total_count}</span>건 입니다.</div>
	<section class="list_table">
		<div class="list_header">
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage'">최근 1개월</button>
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage?m=6'">6개월</button>
			<button onclick="javascript:location.href='${pageContext.request.contextPath}/mypage?m=12'">1년</button>
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
			<c:forEach var="list" items="${mypageInfoModel}" varStatus="status">
				<tr>
					<td>
						<span>${list.reg_cdd}</span>
						<span class="order_num">${list.schedule_seq}</span>
					</td>
					<td>
						<span>${list.status}</span>
					</td>
					<td class="item_info">
						<span>${list.store_name}</span><br>
						<span>${list.menu_name}</span><br>
						<span>예약인원 : ${list.member}명</span><br>
						<span>${list.schedule_year}. ${list.schedule_month}. ${list.schedule_date} / ${list.schedule_hm} ~ ${list.end_time}</span><br>
						<span><em>${list.menu_price}원</em></span>
					</td>
					<td>
						<button>지도보기</button>
					</td>
					<c:if test="${list.status == '결제대기'}">
					<td class="wait">
						<button onclick="javascript:rsvCancelRequest('${list.schedule_seq}')">예약 취소</button>
					</td>
					</c:if>
					<c:if test="${list.status == '결제완료'}">
					<td class="wait">
						<button>결제 취소</button>
						<button>영수증 출력</button>
					</td>
					</c:if>
					<c:if test="${list.status == '사용완료'}">
					<td class="review">
						<button class="like">완전대박</button>
						<button class="dislike">별로에요</button>
						<button>영수증 출력</button>
					</td>
					</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pagination">
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
</tiles:putAttribute>
</tiles:insertDefinition>