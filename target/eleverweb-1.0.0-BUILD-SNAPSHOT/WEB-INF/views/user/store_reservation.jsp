<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="storeTemplate">
<tiles:putAttribute name="title">엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<!-- reservation -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/reservation.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/popup.js"></script>
<script type="text/javascript">$("nav.item_nav ul li:nth-child(3)>a").addClass("active");</script>

<section id="store_reservation">
	<section class="contents">
    	<h2>상품 선택</h2>
		<div class="menu_box">
			<c:forEach var="menulist" items="${menuList}" varStatus="status">
        	<dl class="m_${menulist.menu_seq}">
            	<dt><span>${menulist.menu_name}</span></dt>
                <dd>${menulist.menu_price}원</dd>
            </dl>
            </c:forEach>
        </div>
        <h2>상품 설명</h2>
        <div class="menu_instruction_box">
        	<div class="menu_instruction">
        		<c:forEach var="menulist" items="${menuList}" varStatus="status">
        			<span class="instruction_${menulist.menu_seq}">${menulist.menu_instruction}</span>
        		</c:forEach>
            	<input type="hidden" id="menuID" name="menuID">
            </div>
			<div class="menu_msg">
            	매장의 모든 상품은 예약 시간 2시간 전까지 100% 환불됩니다.<br>
                (단, 웹페이지에서 결제 취소는 예약 당일 전날까지만 가능. 이후 취소를 원하실 경우 매장으로 연락하셔서 취소하시길 바랍니다.)<br>
                예약 시간까지 2시간 미만일 경우 70% 환불 됩니다. 예약시간이 지났을 경우 환불이 불가능한 점 미리 알려드립니다.
            </div>
        </div>
        <h2>예약하기</h2>
        <div class="calendar_wrap">
            <section id="calendar">
            	<span class="reservation_title">예약날짜 선택</span>
                <div class="table_layout">
                    <div class="cal_header">
                        <button class="prev_mon" onclick="javascript:calendarRequest('prev');">이전 달</button>
                        <div class="date_area">
                            <span>0000</span>.<span>00</span>
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
            <section id="rsv_timetable">
            	<span class="reservation_title">예약시간 선택</span>
            	<div id="rsv_member">
	            	<dl>
	            		<dt>예약인원</dt>
	            		<dd>
	            			<select id="reservation_member">
	            				<option value="1">1</option>
	                    		<option value="2" selected="selected">2</option>
	                    		<option value="3">3</option>
	                    		<option value="4">4</option>
	                    		<option value="5">5</option>
	                    		<option value="6">6</option>	
	                    		<option value="7">7</option>
	                    		<option value="8">8</option>
	                    		<option value="9">9</option>
	            			</select>
	            			명
	            		</dd>
	            	</dl>
            	</div>
            	<div id="rsv_list">
	            	<table summary="시간별로 예약자를 확인하실 수 있습니다.">
	                    <caption>시간별 예약자 명단</caption>
	                    <thead>
	                        <tr>
	                            <th scope="col">시간</th>
	                            <th scope="col">방문 예정 / 예약 진행</th>
	                        </tr>
	                    </thead>
	                    <tbody class="timetable">
	                    </tbody>
	                </table>
            	</div>
            	<div class="btn_box">
	            	<dl>
	                	<dt><span class="total_price">총 비용 : 00000원</span></dt>
	            		<dd>
	            			<button class="btn_rsv" onclick="reservationRequest();">예약하기</button>
            			</dd>
	           		</dl>
				</div>
            </section>
       	</div>
	</section>
</section>
<div id="mask">
	<div id="layer">
		<section id="login">
			<section class="login_box">
				<h2>로그인</h2>
				<div class="login_wrap">
					<form name="userLogin" id="userLogin" method="post" onSubmit="return false;">
						<div class="login_input">
							<dl>
								<dt><label for="">아이디</label></dt>
								<dd><input type="text" id="user_email" name="user_email"></dd>
							</dl>
							<dl>
								<dt><label for="">비밀번호</label></dt>
								<dd><input type="password" id="user_password" name="user_password"></dd>
							</dl>
						</div>
					</form>
					
					<div class="login_button">
						<button onClick="javascript:popuplogin();">로그인</button>
					</div>
					<div class="login_footer">
						<div class="checkbox">
							<input id="checkbox_user_email" name="checkbox_user_email" type="checkbox"><label for="">아이디 저장</label>
						</div>
						<div class="find">
							<span><a href="${pageContext.request.contextPath}/find_password">비밀번호찾기</a></span>
						</div>
					</div>
				</div>
				<div class="join_wrap">
					<dl>
						<dt>아직 엘루비 회원이 아니신가요?</dt>
						<dd>
							<a href="${pageContext.request.contextPath}/join"><button>엘루비 회원 가입하기</button></a>
						</dd>
					</dl>
				</div>
			</section>
		</section>
	</div>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>