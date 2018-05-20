<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">gathering list 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script type="text/javascript">

function members(seq){
	event.stopPropagation();
	location.href="member?seq="+seq;
	
}
function deleteListRequest(){
	
	var gathering_seq = "";
	var count = $(".table_layout>table>tbody>tr").size();
	for(var i=0; i < count;i++){
		if($(".table_layout>table>tbody>tr").eq(i).children().children().is(":checked")){
			var seq = $(".table_layout>table>tbody>tr").eq(i).children().children().attr("id");
			seq = seq.slice(4);
			gathering_seq += seq + "/";
		};
	};
	gathering_seq = gathering_seq.slice(0,-1);
	
	var $form = $("<form></form>");
	$form.attr("action", "deleteListRequest");
	$form.attr("method", "post");
	$form.appendTo("action", "body");
	
	req_seq = $("<input type='hidden' name='gathering_seq' value='" + gathering_seq + "'>");
	$form.append(req_seq);
	$form.submit();
	
}
</script>

<section id="coupon_list">
	<section class="list_table">
	
		<div class="btn_box">
			<ul>
				<li><button onclick="javascript:deleteListRequest();">삭제</button></li>
				<li><button onclick="javascript:location.href='write'">새 모임 만들기</button></li>
			</ul>  
		</div>
		
		<div class="table_layout">
			<table summary="Gathering List.">
				<caption>엘루비 기획 모임 관리</caption>
				<thead>
					<tr>
						<th scope="col" class="table_check">선택</th>
						<th scope="col" class="table_seq">번호</th>
						<th scope="col" class="table_name">모임 이름</th>
						<th scope="col" class="table_date">모임 날짜</th>
						<th scope="col" class="table_time">모임 시간</th>
						<th scope="col" class="table_price">모임 가격</th>
						<th scope="col" class="table_max">모임 인원</th>
						<th scope="col" class="table_member">현재 인원</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="_list" items="${list}" varStatus="status">
					<tr>
						<td><input type="checkbox" id="chk_${_list.gathering_seq}"></td>
						<td>${_list.gathering_seq}</td>
						<td onclick="javascript:location.href='modify?seq=${_list.gathering_seq}';">${_list.gathering_name}</td>
						<td>${_list.gathering_ymd}</td>
						<td>${_list.gathering_start_time} ~ ${_list.gathering_end_time}</td>
						<td>${_list.gathering_price}원</td>
						<td>${_list.gathering_max}명</td>
						<td>
							<dl class="dl_status">
								<dt>${_list.gathering_member}명</dt>
								<dd><button onclick="javasccript:members('${_list.gathering_seq}');">자세히보기</button></dd>
							</dl>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
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
				
				 
				
</tiles:putAttribute>
</tiles:insertDefinition>
			
