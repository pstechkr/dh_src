<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="storeTemplate">
<tiles:putAttribute name="title">엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">

<!--  map -->
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=14dfba4851808e2a583c78f8ee1c708b860030c9"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/routemap_main.js"></script>
<script type="text/javascript">$("nav.item_nav ul li:nth-child(1)>a").addClass("active");</script>

<script type="text/javascript" defer="defer">
$(document).ready(function(){
	
	var latitude = $("#store_latitude").val();
	var longitude = $("#store_longitude").val();
	
	loadMap(latitude, longitude);
});

function loadMap(latitude, longitude){
	// 처음 포지션은 한남동 다음
	var position = new daum.maps.LatLng(latitude, longitude); 
	map = new daum.maps.Map(document.getElementById("map"), { 
		center: position, 
		level: 4, 
		mapTypeId: daum.maps.MapTypeId.ROADMAP 
	});
	
	// 줌
	var zoomControl = new daum.maps.ZoomControl();
	map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
	
	//맵 타입
	/* var mapTypeControl = new daum.maps.MapTypeControl();
	map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT); */
	
	// mark
	if(mark != null)
		mark.setVisible(false);
	mark = new daum.maps.Marker({
		position : new daum.maps.LatLng(latitude, longitude) 
	});
	if(marker != null)
		marker.setVisible(false);
	
	mark.setMap(map);
}
</script>
<section id="detail">
	<section class="contents">
    	<section class="store_img">
        </section>
    	<h2>${model.store_name}</h2>
		<div class="info_wrap">
			<div class="map_box">
				<div id="map" class="map"></div>
				<input type="hidden" id="store_latitude" name="store_latitude" value="${model.store_latitude}">
				<input type="hidden" id="store_longitude" name="store_longitude" value="${model.store_longitude}">
			</div>
			<div class="info_box">
				<dl>
					<dt>주소</dt>
					<dd>${model.store_localtitle} ${model.store_localname4}</dd>
				</dl>
				<dl>
					<dt>연락처</dt>
					<dd>
						${model.store_contactnumber}
					</dd>
				</dl>
				<dl>
					<dt>영업시간</dt>
					<dd>
                    	${model.store_openinghours1} ~ ${model.store_openinghours2}
                    	<dl>
                        	<dt>Break Time</dt>
                            <dd>${model.store_breaktime1} ~ ${model.store_breaktime2}</dd>
                    	</dl>
                    </dd>
				</dl>
				<dl>
					<dt>정기휴무일</dt>
					<dd>${model.store_dayoff}</dd>
				</dl>
				<dl>
					<dt>주차정보</dt>
					<dd>${model.store_parkinginfo}</dd>
				</dl>
			</div>
		</div>
        
		<section class="introduce">
        	<span>${model.store_intromessage}</span>
		</section>
	</section>
</section>

</tiles:putAttribute>
</tiles:insertDefinition>
