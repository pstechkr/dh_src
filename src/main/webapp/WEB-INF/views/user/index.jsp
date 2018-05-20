<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="indexTemplate">
<tiles:putAttribute name="title">이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<section id="index">
	<section class="main_visual">
		<div id="shadow_wrap">
			<div class="shadow left"></div>
			<div class="shadow right"></div>
		</div>
		<div id="slider">
			<ul>
				<c:forEach var="list" items="${sliderList}" varStatus="status">
				<li>
					<c:if test="${list.slider_link != ''}">
					<a href="${list.slider_link}">
					</c:if>
					<img src="${list.slider_image}" alt="${list.slider_comment}">
					<c:if test="${list.slider_link != ''}">
					</a>
					</c:if>
				</li>
				</c:forEach>
			</ul>
			<div class="control">
				<div id="prev_btn"><a href="#">이전</a></div>
				<div id="next_btn"><a href="#">다음</a></div>
			</div>
		</div>
	</section>
	<section class="contents">
		<!-- store -->
		<!-- 
		<ul class="items">
			<c:forEach var="storelist" items="${storeList}" varStatus="status">
			
			<li onclick="javascript:location.href='store/${storelist.store_seq}/intro'">
				<span class="outline"></span>
				<div class="item_cover">
					<div class="photo"><img src="${pageContext.request.contextPath}/resources/img/main/main.jpg" alt="Css Template Preview" /></div>
					<div class="item_info">
						<div class="item_place"><span>${storelist.store_localname1}</span></div>
						<div class="item_people">
							<span>예약</span>
							<span>${storelist.store_point}</span>
						</div>
					</div>
				</div>
				<div class="item_footer">
					<div class="item_brand"><span>${storelist.store_name}</span></div>
					<div class="like_btn"><a href="#">좋아요</a></div>
				</div>
			</li>
			</c:forEach>
		</ul> -->
		<h2><span class="dd5858">현재 진행중</span>인 엘루비 프로젝트</h2>
		<ul class="items">
			<c:forEach var="_list" items="${nowGatheringList}" varStatus="status">
			
			<li onclick="javascript:location.href='gathering/${_list.gathering_seq}'">
				<span class="outline"></span>
				<div class="item_cover">
					<div class="photo"><img src="${_list.gathering_sub_image}" alt="이색 데이트 엘루비" /></div>
					<div class="item_info">
						<div class="item_place"><span>${_list.gathering_ymd}</span></div>
						<div class="item_people">
							<span>${_list.gathering_localname2}</span>
						</div>
					</div>
				</div>
				<div class="item_footer">
					<div class="item_brand"><span>${_list.gathering_name}</span></div>
					<!-- <div class="like_btn"><a href="#">좋아요</a></div> -->
				</div>
			</li>
			</c:forEach>
			<%-- preparation --%>
			<li>
				<span class="outline"></span>
				<div class="item_cover">
					<div class="photo"><img src="${pageContext.request.contextPath}/resources/img/main/preparation.png" alt="이색 데이트 엘루비" /></div>
					<div class="item_info">
						<div class="item_place"><span>준비중 입니다.</span></div>
						<div class="item_people">
							<span></span>
						</div>
					</div>
				</div>
				<div class="item_footer">
					<div class="item_brand"><span>다음 엘루비도 기대해 주세요!</span></div>
					<!-- <div class="like_btn"><a href="#">좋아요</a></div> -->
				</div>
			</li>
			<li>
				<span class="outline"></span>
				<div class="item_cover">
					<div class="photo"><img src="${pageContext.request.contextPath}/resources/img/main/preparation.png" alt="이색 데이트 엘루비" /></div>
					<div class="item_info">
						<div class="item_place"><span>준비중 입니다.</span></div>
						<div class="item_people">
							<span></span>
						</div>
					</div>
				</div>
				<div class="item_footer">
					<div class="item_brand"><span>다음 엘루비도 기대해 주세요!</span></div>
					<!-- <div class="like_btn"><a href="#">좋아요</a></div> -->
				</div>
			</li>
			
		</ul>
		
		<h2><span class="dd5858">지난</span> 엘루비 프로젝트</h2>
		
		<ul class="items">
			<c:forEach var="_list" items="${pastGatheringList}" varStatus="status">
			
			<li onclick="javascript:location.href='gathering/${_list.gathering_seq}'">
				<span class="outline"></span>
				<div class="item_cover">
					<div class="photo"><img src="${_list.gathering_sub_image}" alt="이색 데이트 엘루비" /></div>
					<div class="item_info">
						<div class="item_place"><span>${_list.gathering_ymd}</span></div>
						<div class="item_people">
							<span>${_list.gathering_localname2}</span>
						</div>
					</div>
				</div>
				<div class="item_footer">
					<div class="item_brand"><span>${_list.gathering_name}</span></div>
					<!-- <div class="like_btn"><a href="#">좋아요</a></div> -->
				</div>
			</li>
			</c:forEach>
		</ul>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>
