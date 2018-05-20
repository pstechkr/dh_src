<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">user list 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<script type="text/javascript">
function couponRequest(){
	
	var user_seq = "";
	var count = $(".table_layout>table>tbody>tr").size();
	for(var i=0; i < count;i++){
		if($(".table_layout>table>tbody>tr").eq(i).children().children().is(":checked")){
			var seq = $(".table_layout>table>tbody>tr").eq(i).children().children().attr("id");
			seq = seq.slice(4);
			user_seq += seq + "/";
		};
	};
	user_seq = user_seq.slice(0,-1);
	
	var $form = $("#couponInfo");
	$form.attr("action", "couponRequest");
	$form.attr("method", "post");
	user_seq = $("<input type='hidden' name='user_seq' value='" + user_seq + "'>");
	$form.append(user_seq);
	$form.submit();
	
}
</script>
<section id="user_list">
	<section class="list_header">
		<div class="coupon_box">
			<form name="couponInfo" id="couponInfo" method="post"">
				<dl class="c_name">
					<dt>쿠폰명</dt>
					<dd><input type="text" name="coupon_name" value="test"></dd>
				</dl>
				<dl class="c_limit">
					<dt>쿠폰기간</dt>
					<dd>
						<input type="text" name="coupon_limit" value="2014-08-01">
						<select name="coupon_limit_div" value="0">
							<option value="0">날짜</option>
							<option value="1">개월</option>
						</select>
						<button>날짜선택</button>
					</dd>
				</dl>
				<dl class="c_price">
					<dt>쿠폰가</dt>
					<dd>
						<input type="text" name="coupon_price" value="1000">
						<select name="coupon_div">
							<option value="0">원</option>
							<option value="1">퍼센트</option>
						</select>
					</dd>
				</dl>
			</form>
		</div>
		<div class="btn_box">
			<ul>
				<button onclick="javascript:couponRequest();">쿠폰 발행</button>
			</ul>
		</div>
		<div style="clear:both">
		</div>
	</section>
	<section class="list_table">
		<div class="table_layout">
			<table summary="User List.">
				<caption>엘루비 일반 회원(이용자) 리스트</caption>
				<thead>
					<tr>
						<th scope="col" class="table_check">선택</th>
						<th scope="col" class="table_seq">번호</th>
						<th scope="col" class="table_email">이메일</th>
						<th scope="col" class="table_name">이름</th>
						<th scope="col" class="table_gender">성별</th>
						<th scope="col" class="table_birthday">생년월일</th>
						<th scope="col" class="table_phoneno">연락처</th>
						<th scope="col" class="table_facebookid">페이스북</th>
						<th scope="col" class="table_reg_cdd">가입일자</th>
						<th scope="col" class="table_recent_login">최근로그인</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="_list" items="${list}" varStatus="status">
					<tr>
						<td><input type="checkbox" id="chk_${_list.user_seq}"></td>
						<td>${_list.user_seq}</td>
						<td>${_list.user_email}</td>
						<td>${_list.user_name}</td>
						<td>${_list.user_gender}</td>
						<td>${_list.user_birthday}</td>
						<td>${_list.user_phoneno}</td>
						<td>${_list.facebook_id}</td>
						<td>${_list.reg_cdd}</td>
						<td class="tbody_recent_login">${_list.recent_cdd}<br>${_list.recent_cdt}</td>
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
			
