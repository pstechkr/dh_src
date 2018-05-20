<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="communityTemplate">
<tiles:putAttribute name="title">${reviewInfoModel.review_name} :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<!-- jssor slider -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slider/jssor.slider.js"></script>
<script type="text/javascript" charset="utf-8">
window.onload = function() {
	
	var latitude = ${reviewInfoModel.review_latitude};
	var longitude = ${reviewInfoModel.review_longitude};

	var position = new daum.maps.LatLng(latitude, longitude);
	var map = new daum.maps.StaticMap(document.getElementById("map"), {
		center: position,
		level: 6,
		mapTypeId: daum.maps.MapTypeId.ROADMAP,
			marker: [
                     	{
                            position: position,
                            text: '${reviewInfoModel.review_name}'
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
<section id="review_main">
	<section class="contents">
        <!-- 정보 박스 -->
        <h2>기본 정보</h2>
        <div class="info_wrap">
        	<div class="review_info box">
        		<div class="name_box">
        		${reviewInfoModel.review_name}
        		</div>
        		<dl class="division">
                	<dt>구분 :</dt>
                    <dd>${reviewInfoModel.review_division}</dd>
                </dl>
                <dl class="time">
                	<dt>영업시간 :</dt>
                    <dd>${reviewInfoModel.review_time}</dd>
                </dl>
                <dl class="holiday">
                	<dt>휴일 :</dt>
                    <dd>${reviewInfoModel.review_holiday}</dd>
                </dl>
                <dl class="price">
                	<dt>예산 :</dt>
                    <dd>${reviewInfoModel.review_price}</dd>
                </dl>
                <dl class="parking">
                	<dt>주차 :</dt>
                    <dd>${reviewInfoModel.review_parking}</dd>
                </dl>
               <!--  <div class="btn_box">
                	<button class="btn_full">그러나 협찬 따위는 없다고 한다.</button>
                </div> -->
            </div>
            
            <!-- 지도 박스 -->
            <div class="review_map box">
            	<div id="map" class="map"></div>
            	<div class="map_bottom"><span>${reviewInfoModel.review_localtitle}</span></div>
            </div>
            <div class="clear"></div>
		</div>
		
		<c:if test="${isSlider == true}">
		<h2>엘루비 리포트</h2>
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
