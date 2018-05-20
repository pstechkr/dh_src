<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="communityTemplate">
<tiles:putAttribute name="title">몽이의 맛동산 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pagination.js"></script>
<section id="review">
	<section class="contents">
    	<ul>
    		<c:forEach var="_list" items="${list}" varStatus="status">
        	<li onclick="javascript:location.href='list/${_list.review_seq}'">
	            <span class="outline"></span>
            	<div class="item_cover">
                	<div class="photo">
                    	<img src="${_list.review_main_image}" alt="이색 데이트 엘루비"> 
                    </div>
                    <div class="item_info">
                    	<div class="item_place">
                        	<span>${_list.review_division}</span>
                    	</div>
                        <div class="item_location">
                        <span>${_list.review_localname2}</span>
                        </div>
                	</div>
                </div>
                <div class="item_footer">
                	<div class="item_brand">
                    	<span>${_list.review_name}</span>
                    </div>
                </div>
            </li>
            </c:forEach>
		</ul>
		
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
