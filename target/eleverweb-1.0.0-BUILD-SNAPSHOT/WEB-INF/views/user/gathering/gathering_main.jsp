<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="gatheringTemplate">
<tiles:putAttribute name="title">${gatheringInfoModel.gathering_name} :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<!-- jssor slider -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.slider.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/map.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/popup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/facebook.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/idCookie.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/gathering.js"></script>
<script type="text/javascript" charset="utf-8">
window.onload = function() {
	var latitude = ${gatheringInfoModel.gathering_latitude};
	var longitude = ${gatheringInfoModel.gathering_longitude};

	var position = new daum.maps.LatLng(latitude, longitude);
	var map = new daum.maps.StaticMap(document.getElementById("map"), {
		center: position,
		level: 6,
		mapTypeId: daum.maps.MapTypeId.ROADMAP,
			marker: [
                     	{
                            position: position,
                            text: '${gatheringInfoModel.gathering_place}'
                        }
                    ]
	});
	var options = {

            $AutoPlay: false,                                   //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
            $DragOrientation: 1                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
        	,
			$ArrowNavigatorOptions: {                       //[Optional] Options to specify and enable arrow navigator or not
                $Class: $JssorArrowNavigator$,              //[Requried] Class to create arrow navigator instance
                $ChanceToShow: 2                               //[Required] 0 Never, 1 Mouse Over, 2 Always
            },
	};
    var jssor_slider1 = new $JssorSlider$("slider1_container", options);
	
};
</script>
<section id="gathering">
	<section class="gathering_image">
	    <img src="${gatheringInfoModel.gathering_main_image}">
    </section>
    
    <section class="contents">
    
		<!-- 정보 박스 -->
        <div class="info_wrap">
        	<div class="gathering_info box">
                <dl class="date">
                	<dt>일시 :</dt>
                    <dd>${gatheringInfoModel.gathering_ymd} ${gatheringInfoModel.gathering_start_time} ~ ${gatheringInfoModel.gathering_end_time}</dd>
                </dl>
                <dl class="place">
                	<dt>장소 :</dt>
                    <dd>${gatheringInfoModel.gathering_place}</dd>
                </dl>
                <dl class="price">
                	<dt>가격 :</dt>
                    <dd>1인당 ${gatheringInfoModel.gathering_price}원</dd>
                </dl>
                <dl class="member">
                	<dt>참여 인원 :</dt>
                    <dd>최대 ${gatheringInfoModel.gathering_max}명 (현재 ${gatheringInfoModel.gathering_member}명 신청 중)</dd>
                </dl>
                <dl class="time_limit">
                	<dt>신청 기간 :</dt>
                    <dd>${gatheringInfoModel.gathering_limit_ymd}까지 ${gatheringInfoModel.gathering_to_days}</dd>
                </dl>
                <div class="btn_box">
                	<c:choose>
                	<c:when test="${gatheringInfoModel.gathering_to_days != null}">
						<c:choose>
                		<c:when test="${isApply}">
	          	   			<select id="member">
		               			<option value="">선택</option>
		               			<c:forEach var="applyNum" begin="1" end="${apply}" varStatus="status">
		               			<option value="${status.count}">${status.count}</option>
		               			</c:forEach>
		               		</select> 명
               				<button class="btn_rsv" onclick="javascript:reservationRequest('${gatheringInfoModel.gathering_seq}');">참여하기<img src="${pageContext.request.contextPath}/resources/img/smile.png"></button>	
              			</c:when>
               			<c:otherwise>
          					<button class="btn_full">모집이 마감 되었습니다.</button>
               			</c:otherwise>
               			</c:choose>
					</c:when>                	
                	<c:otherwise>
                			<button class="btn_full">신청 기간이 지났습니다.</button>
                	</c:otherwise>
                	</c:choose>
                
                </div>
            </div>
            
            <!-- 지도 박스 -->
            <div class="gathering_map box">
            	<div id="map" class="map"></div>
            	<div class="map_bottom"><span>${gatheringInfoModel.gathering_localtitle}</span></div>
            </div>
            <div class="clear"></div>
		</div>

		<c:if test="${isSlider == true}">
		<!-- 내용 박스 -->
        <div class="review_slider box">
        	<div id="slider1_container">
		        <!-- Slides Container -->
		        <div u="slides" class="slides">
		        	<c:forEach var="_sliderList" items="${sliderList}" varStatus="status">
		            	<div><img u="image" src="${_sliderList}"/></div>
		            </c:forEach>
		        </div>
		        <!-- Arrow Navigator Skin Begin -->
		        <!-- Arrow Left -->
		        <span u="arrowleft" class="jssora05l" style="width: 40px; height: 40px; top: 300px; left: 8px;">
		        </span>
		        <!-- Arrow Right -->
		        <span u="arrowright" class="jssora05r" style="width: 40px; height: 40px; top: 300px; right: 8px">
		        </span>
    		</div>
      	</div>
		</c:if>
		<!-- 내용 박스 -->
        <div class="gathering_instruction box">
        	${gatheringInfoModel.gathering_instruction}
        </div>
        
    </section>
</section>

<!-- 팝업 로그인 -->
<div id="mask">
	<div id="layer">
		<section id="login">
			<section class="login_box inbox">
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
						<button class="general" onClick="javascript:popuplogin('${gatheringInfoModel.gathering_seq}');">로그인</button>
						<button class="facebook" onClick="javascript:popupfacebooklogin('${gatheringInfoModel.gathering_seq}');">Facebook 로그인</button>
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
