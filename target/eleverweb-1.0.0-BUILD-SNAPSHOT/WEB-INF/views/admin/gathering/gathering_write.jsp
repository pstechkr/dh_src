<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">gathering write 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<!--  jQuery UI -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/routemap_gathering.js"></script>
<!-- tinymce -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/tinymce/tinymce.min.js" charset="utf-8"></script>
<script type="text/javascript">
// editor 생성
$(document).ready(function(){
	// 지도 활성화
	loadMap();
	
	// tinymce 시작 
	tinymce.init({
		language: "ko_KR",
		editor_selector : "editor",
		mode: "specific_textareas",
	    menubar: false,
	    statusbar: true,
	    plugins: [
	       		"advlist autolink link image lists charmap print preview spellchecker pagebreak anchor",
	       		"searchreplace contextmenu visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
	       		"save table emoticons textcolor" ],
	    toolbar_items_size: 'small',
	    toolbar1: "newdocument | fontselect fontsizeselect | bold italic underline strikethrough | forecolor backcolor | alignleft aligncenter alignright alignjustify | table insertdatetime",
		toolbar2: "undo redo | searchreplace | bullist numlist | outdent indent blockquote | link unlink image media code | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | imageupload | preview",
		// file_browser_callback: myCustomFileBrowser,
		relative_urls: false,
		content_css: "/eleverweb/resources/css/mce.css",
		/* object_resizing : "img", */
		// 에디터 높이, 넓이 설정
		width : 900,
		height: 400
	});
	
	// datepicker
	$("#datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		changeMonth: true,
		yearRange: "c-100:c",
		changeYear: true,
		showMonthAfterYear: true,
		showOn: "button",
		showAnim: "slideDown",
		minDate:0
	});
	$("#datepicker_start").datepicker({
		dateFormat: 'yy-mm-dd',
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		changeMonth: true,
		yearRange: "c-100:c",
		changeYear: true,
		showMonthAfterYear: true,
		showOn: "button",
		showAnim: "slideDown",
		minDate:0
	});
	$("#datepicker_end").datepicker({
		dateFormat: 'yy-mm-dd',
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		changeMonth: true,
		yearRange: "c-100:c",
		changeYear: true,
		showMonthAfterYear: true,
		showOn: "button",
		showAnim: "slideDown",
		minDate:0
	});

	// 데이트 피커 버튼에 글씨 넣기
	$(".ui-datepicker-trigger").html("날짜선택");
	
	// 클릭 이벤트
	$("#datepicker").on({
		change: function (event){			
			var date = $(this).val(); 
			$("#gathering_ymd").val(date);
		}
	});
	$("#datepicker_end").on({
		change: function (event){			
			var date = $(this).val(); 
			$("#gathering_limit_ymd").val(date);
		}
	});
	
	var tmp = $("#gathering_slide_image").val().replace(/<br>/g,"\n"); // 엔터 <br>로 변경
	$("#gathering_slide_image").val(tmp);
	
	
});
</script>
<script type="text/javascript">
function writeContents(){
	
	/* // checkbox 체크된 것
	var checkboxCnt = $("input:checkbox[name='elever_tag']").length;
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
	$("#gathering_tag").val(checklist); */
	
	var tmp = $("#gathering_slide_image").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	tmp = tmp.replace(/(\s*)/g,"");
	$("#gathering_slide_image").val(tmp);
	
	var form = document.getElementById("gatheringInfo");
	form.action = "writeRequest";
	form.submit();
}
function modifyContents(){
	
	var tmp = $("#gathering_slide_image").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	tmp = tmp.replace(/(\s*)/g,"");
	$("#gathering_slide_image").val(tmp);
	
	var form = document.getElementById("gatheringInfo");
	form.action = "modifyRequest";
	form.submit();
	
}


function loadMap(){
	
	var lng = ""; 
	var lat = "";
	
	if($("#gathering_latitude").val() != ""){
		
		lng = $("#gathering_longitude").val();
		lat = $("#gathering_latitude").val();
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

<section id="gathering_write">
	<section class="contents">
		<form name="gatheringInfo" id="gatheringInfo" method="post" onsubmit="return false;">
		
			<div class="info_wrap">
				<h2>기본 정보 입력</h2>
				<dl class="name">
					<dt>모임 제목</dt>
					<dd><input type="text" id="gathering_name" name="gathering_name" chkModify value="${gatheringInfoModel.gathering_name}"></dd>
				</dl>
				
				<dl class="date">
					<dt>모임 날짜</dt>
					<dd><input type="text" id="gathering_ymd" name="gathering_ymd" chkModify value="${gatheringInfoModel.gathering_ymd}">
					<input type="hidden" id="datepicker"/></dd>
				</dl>
				
				<dl class="place">
					<dt>모임 장소</dt>
					<dd><input type="text" id="gathering_place" name="gathering_place" chkModify value="${gatheringInfoModel.gathering_place}"></dd>
				</dl>
				
				<dl class="address">
              		<dt>지도 및 주소 :</dt>
              		<dd>
						<input type="text" id="q" value="번지 수까지 입력 가능합니다." onfocus="this.value='';"><button class="btn_find" onclick="searchPosition('q')">검색</button>
						<input type="hidden" id="gathering_longitude" name="gathering_longitude" chkModify value="${gatheringInfoModel.gathering_longitude}">
						<input type="hidden" id="gathering_latitude" name="gathering_latitude" chkModify value="${gatheringInfoModel.gathering_latitude}">
						<input type="hidden" id="gathering_localname1" name="gathering_localname1" chkModify value="${gatheringInfoModel.gathering_localname1}">
						<input type="hidden" id="gathering_localname2" name="gathering_localname2" chkModify value="${gatheringInfoModel.gathering_localname2}">
						<input type="hidden" id="gathering_localname3" name="gathering_localname3" chkModify value="${gatheringInfoModel.gathering_localname3}">
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
									<span>기본주소</span><input type="text" id="gathering_localtitle" name="gathering_localtitle" chkModify value="${gatheringInfoModel.gathering_localtitle}" readonly="readonly">
									<span>상세주소</span><input type="text" id="gathering_localname4" name="gathering_localname4" chkModify value="${gatheringInfoModel.gathering_localname4}">
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
					<dt>모임 가격</dt>
					<dd><input type="text" id="gathering_price" name="gathering_price" chkModify value="${gatheringInfoModel.gathering_price}"></dd>
				</dl>
				
				<dl class="member">
					<dt>모집 인원</dt>
					<dd><input type="text" id="gathering_max" name="gathering_max" chkModify value="${gatheringInfoModel.gathering_max}"></dd>
				</dl>
				
				<dl class="time">
					<dt>모임 시간</dt>
					<dd>
						시작시간<input type="text" id="gathering_start_time" name="gathering_start_time" chkModify value="${gatheringInfoModel.gathering_start_time}">
						종료시간<input type="text" id="gathering_end_time" name="gathering_end_time" chkModify value="${gatheringInfoModel.gathering_end_time}">
					</dd>
				</dl>
				
				<dl class="limit">
					<dt>모집 기간</dt>
					<dd>
						<input type="text" id="gathering_limit_ymd" name="gathering_limit_ymd" chkModify value="${gatheringInfoModel.gathering_limit_ymd}">까지 <input type="hidden" id="datepicker_end"/>
					</dd>
				</dl>
				
				<dl class="main_image">
					<dt>메인 이미지</dt>
					<dd>
						<input type="text" id="gathering_main_image" name="gathering_main_image" value="${gatheringInfoModel.gathering_main_image}">(900px * 330px)
						<c:if test="${gatheringInfoModel.gathering_main_image != null}">
							<br><img src="${gatheringInfoModel.gathering_main_image}" style="width:400px;">
						</c:if>
					</dd>
				</dl>
				
				<dl class="sub_image">
					<dt>서브 이미지(카드 이미지)</dt>
					<dd>
						<input type="text" id="gathering_sub_image" name="gathering_sub_image" value="${gatheringInfoModel.gathering_sub_image}">(275px * 295px)
						<c:if test="${gatheringInfoModel.gathering_sub_image != null}">
							<br><img src="${gatheringInfoModel.gathering_sub_image}">
						</c:if>
					</dd>
				</dl>
				
				<dl class="slide_image">
					<dt>슬라이드 이미지</dt>
					<dd>
						<textarea id="gathering_slide_image" name="gathering_slide_image" style="width:698px;min-height:100px;">${gatheringInfoModel.gathering_slide_image}</textarea>(800px * 600px)
						
					</dd>
				</dl>
			<%-- 	<dl class="tag">
                	<dt>태그 선택 :</dt>
                    <dd>
                    	<c:forEach var="list" items="${tag_list}" varStatus="status">
	                    	<input type="checkbox" name="elever_tag" value="${list.tag_seq}"><img src="${pageContext.request.contextPath}/${list.tag_image_default}">
                    	</c:forEach>
                    	<input type="hidden" id="gathering_tag" name="gathering_tag" value="">
                    </dd>
                </dl> --%>
			</div>
			
			<div class="editor_wrap">
				<h2>내용 입력</h2>
				<textarea id="gathering_instruction" name="gathering_instruction" class="editor">${gatheringInfoModel.gathering_instruction}</textarea>
			</div>
			
			<div class="btn_box">
				<c:choose>
					<c:when test="${gatheringInfoModel.gathering_name == null}">
						<button onclick="javascript:writeContents();">신규 등록</button>
					</c:when>
					<c:otherwise>
						<c:if test="${param.seq != null}">
							<input type="hidden" name="gathering_seq" value="${param.seq}">
						</c:if>
						<button onclick="javascript:modifyContents();">수정확인</button>
						<button onclick="javascript:writeContents();">위 내용을 신규로 등록</button>
					</c:otherwise>
				</c:choose>
			</div>
		
		</form>
		
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>
			
