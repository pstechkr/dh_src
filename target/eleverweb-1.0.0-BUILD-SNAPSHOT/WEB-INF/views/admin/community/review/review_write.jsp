<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">review write 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<!--  jQuery UI -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/routemap_review.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	// 지도 활성화
	loadMap();
});
function writeContents(){
	
	// checkbox 체크된 것
	/* var checkboxCnt = $("input:checkbox[name='elever_tag']").length;
	var checklist = "";
	if(checkboxCnt > 0){
		for(var i=0; i < checkboxCnt; i++){
			if($("input:checkbox[name='elever_tag']").eq(i).is(":checked")){
				checklist += $("input:checkbox[name='elever_tag']").eq(i).val();
				checklist += "/";
			}
		}
		checklist = checklist.slice(0, -1);
	}
	$("#review_tag").val(checklist); */
	
	
	var tmp = $("#review_slide_image").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	tmp = tmp.replace(/(\s*)/g,"");
	$("#review_slide_image").val(tmp);
	
	var form = document.getElementById("reviewInfo");
	form.action = "writeRequest";
	form.submit();	
}
function modifyContents(){
	
	var form = document.getElementById("reviewInfo");
	form.action = "modifyRequest";
	form.submit();
	
}
function loadMap(){
	
	var lng = ""; 
	var lat = "";
	
	
	if($("#review_latitude").val() != ""){
		
		lng = $("#review_longitude").val();
		lat = $("#review_latitude").val();
	}
	
	var position = "";
	
	if(lng == "" || lat == ""){
		position = new daum.maps.LatLng(37.537123, 127.005523);
	}else{
		position = new daum.maps.LatLng(lat, lng);
	}
 
	// 맵 생성
	map = new daum.maps.Map(document.getElementById("map"), { 
		center: position, 
		level: 3, 
		mapTypeId: daum.maps.MapTypeId.ROADMAP 
	});
	
	if(mark != null)
		mark.setVisible(false);
	var po = new daum.maps.LatLng(lat, lng);
	map.setCenter(po);

	if(marker != null)
		marker.setVisible(false);
	marker = new daum.maps.Marker({
		position: po
	});
	marker.setMap(map);
	
	// 줌
	var zoomControl = new daum.maps.ZoomControl();
	map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
	
	//맵 타입
	var mapTypeControl = new daum.maps.MapTypeControl();
	map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
}
</script>

<section id="review_write">
	<section class="contents">
		<form name="reviewInfo" id="reviewInfo" method="post" onsubmit="return false;">
		
			<div class="info_wrap">
				<h2>기본 정보 입력</h2>
				<dl class="name">
					<dt>매장 이름</dt>
					<dd><input type="text" id="review_name" name="review_name" chkModify value="매장명"></dd>
				</dl>
				
				<dl class="division">
					<dt>구분</dt>
					<dd><input type="text" id="review_division" name="review_division" chkModify value="구분">
				</dl>
				
				<dl class="time">
					<dt>영업 시간</dt>
					<dd><input type="text" id="review_time" name="review_time" chkModify value="영업시간 어쩌고저쩌고">
				</dl>
				
				<dl class="holiday">
					<dt>휴일</dt>
					<dd><input type="text" id="review_holiday" name="review_holiday" chkModify value="연중무휴"></dd>
				</dl>
				
				<dl class="address">
              		<dt>지도 및 주소 :</dt>
              		<dd>
						<input type="text" id="q" value="번지 수까지 입력 가능합니다." onfocus="this.value='';"><button class="btn_find" onclick="searchPosition('q')">검색</button>
						<input type="hidden" id="review_longitude" name="review_longitude" chkModify value="${reviewInfoModel.review_longitude}">
						<input type="hidden" id="review_latitude" name="review_latitude" chkModify value="${reviewInfoModel.review_latitude}">
						<input type="hidden" id="review_localname1" name="review_localname1" chkModify value="${reviewInfoModel.review_localname1}">
						<input type="hidden" id="review_localname2" name="review_localname2" chkModify value="${reviewInfoModel.review_localname2}">
						<input type="hidden" id="review_localname3" name="review_localname3" chkModify value="${reviewInfoModel.review_localname3}">
						<div class="map_wrap">
							<div class="map_left">
								<div class="search_title">
									<span>검색결과</span>
								</div>
								<div id="searchResultB">
									<div class="search_default">
										<span>검색 결과가 없습니다.
										</span>
									</div>
								</div>
								<div class="search_last_title">
									<span>주소입력</span>
								</div>
								<div class="search_last">
									<span>기본주소</span><input type="text" id="review_localtitle" name="review_localtitle" chkModify value="${reviewInfoModel.review_localtitle}" readonly="readonly">
									<span>상세주소</span><input type="text" id="review_localname4" name="review_localname4" chkModify value="${reviewInfoModel.review_localname4}">
								</div>
							</div>
							<div class="map_right">
								<div class="searchResultA">
									<div id="map"></div>
								</div>
							</div>
						</div>
              		</dd>
              	</dl>
				
				<dl class="price">
					<dt>예산</dt>
					<dd><input type="text" id="review_price" name="review_price" chkModify value="만원에서 이마넌"></dd>
				</dl>
				
				<dl class="parking">
					<dt>주차</dt>
					<dd><input type="text" id="review_parking" name="review_parking" chkModify value="주차가능"></dd>
				</dl>
				
				<dl class="main_image">
					<dt>메인 이미지 경로</dt>
					<dd><input type="text" id="review_main_image" name="review_main_image" chkModify value=""></dd>
				</dl>
				
			</div>
			
			<div class="image_wrap">
				<h2>이미지 입력</h2>
				<textarea id="review_slide_image" name="review_slide_image" class="editor"></textarea>
			</div>
			
			<div class="btn_box">
				<button onclick="javascript:writeContents();">입력</button>
				<%-- <c:choose>
					<c:when test="${gatheringInfoModel.gathering_name == null}">
						<button onclick="javascript:writeContents();">입력</button>
					</c:when>
					<c:otherwise>
						<c:if test="${param.seq != null}">
							<input type="hidden" name="gathering_seq" value="${param.seq}">
						</c:if>
						<button onclick="javascript:modifyContents();">입력</button>
					</c:otherwise>
				</c:choose> --%>
			</div>
		
		</form>
		
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>
			
