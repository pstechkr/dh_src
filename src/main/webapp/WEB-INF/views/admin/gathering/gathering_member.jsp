<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">gathering list 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script type="text/javascript">
function statusRequest(schedule_seq){
	
	var request = {};
	var select_status = $("." + schedule_seq + ">td>dl.dl_status>dd>select").val();
	
	request["schedule_seq"] = schedule_seq;
	request["status"] = select_status;
	
	switch (select_status){
		case "1": if($("." + schedule_seq + ">td>dl.dl_status>dt").text() == "결제대기"){alert("현재 상태와 같습니다.");return false;} break;
		case "2": if($("." + schedule_seq + ">td>dl.dl_status>dt").text() == "결제완료"){alert("현재 상태와 같습니다.");return false;} break;
		case "3": if($("." + schedule_seq + ">td>dl.dl_status>dt").text() == "사용완료"){alert("현재 상태와 같습니다.");return false;} break;
		case "4": if($("." + schedule_seq + ">td>dl.dl_status>dt").text() == "취소"){alert("현재 상태와 같습니다.");return false;} break;
	}
	
	$.ajax({
		url: "statusRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
	    	
	    	if(response["isCheck"]){
	    		
	    		$("." + schedule_seq + ">td>dl.dl_status>dt").html(response["changed"]);
	    		
	    	}
	    	
	    	
	    },
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end
} // function end
</script>

<section id="gathering_member">
	<section class="list_table">
	
		<div class="btn_box">
			<div class="left">
				<button class="toList" onclick="javascript:location.href='list'">목록으로</button>
				<h1>${gathering_name}</h1>
			</div>
			<div class="right">
				<input class="inputSearch" type="text" name="q">
				<button class="btnSearch" onclick="javascript:alert('될리가 있냐?')">검색</button>
			</div>
		</div>
		
		<!-- <div class="btn_box">
			<button onclick="javascript:location.href='write'">새 모임 만들기</button>  
		</div> -->
		
		<div class="table_layout">
			<table summary="Gathering Member List.">
				<caption>엘루비 기획 모임 관리</caption>
				<thead>
					<tr>
						<th scope="col" class="date">예약날짜</th>
						<th scope="col" class="name">예약자명</th>
						<th scope="col" class="phoneno">연락처</th>
						<th scope="col" class="member">예약인원</th>
						<th scope="col" class="price">가격</th>
						<th scope="col" class="account_way">결제방식</th>
						<th scope="col" class="status">상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${gatheringMemberList}" varStatus="status">
					<tr class="onelist ${list.schedule_seq}">
						<td class="tbody_date">${list.reg_cdd}<br>${list.reg_cdt}</td>
						<td>${list.user_name}</td>
						<td>${list.user_phoneno}</td>
						<td>${list.member}명</td>
						<td>${list.price}원</td>
						<td>${list.account_way}</td>
						<td>
							<dl class="dl_status">
								<dt>${list.status}</dt>
								<dd><select>
										<option value="1">결제대기</option>
										<option value="2">결제완료</option>
										<option value="3">사용완료</option>
										<option value="4">취소</option>
									</select>
									<button onclick="statusRequest('${list.schedule_seq}');">변경</button>
								</dd>
							</dl> 
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%-- 
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
		</div> --%>
		
	</section>
</section>
				
				 
				
</tiles:putAttribute>
</tiles:insertDefinition>
			
