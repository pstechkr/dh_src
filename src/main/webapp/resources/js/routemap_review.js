/*
 * 자바스크립트 상수 선언
 * */

var isTest = 1;
//var apiKey = "80c0bbc7e9db2e1b68b301e0c90264ecdbfdeb1d";
var protocol=(location.protocol=="https:"?"https:":"http:");
var addrToCoordApiAddr = protocol+"//apis.daum.net/local/geo/addr2coord?apikey=";
var coordToAddrApiAddr = protocol+"//apis.daum.net/local/geo/coord2addr?apikey=";
var addrSearchCallback = "pongSearch";
var searchResultBody = "searchResultB";
var searchResultHeader = "searchResultH";
var roadViewAddrHeader = "rvAddr";

if( window.console == undefined ){ console = { log : function(){} }; }

//지도API키
var _MAP_DNA_API_KEY = "3f47deb5cc63b7793c9c7531ced38a7f203f1d8d";		// 실제
var _MAP_DNADEV_API_KEY = "14dfba4851808e2a583c78f8ee1c708b860030c9";	// 개발용		
var _MAP_LOCALHOST_API_KEY  = "";

//로컬API키
var _LOCAL_DNA_API_KEY = "6e30964ab472ae0f3daad5933800fedf11496623";		// 실제
var _LOCAL_DNADEV_API_KEY = "de3b44a454fb67c10a48c4a0868945cd8edda17c";		//개발용
var _LOCAL_LOCALHOST_API_KEY = "";	

var marker;		//주소 선택시 생기는 마커
var mark;		//click 시 생기는 마커
var map;
var po;
var infoWindow;

function getMapApiKey(){
	switch(window.location.hostname) {
		case 'dna.daum.net' :
			return _MAP_DNA_API_KEY;
		case 'dna.dev.daum.net' :
			return _MAP_DNADEV_API_KEY;
		default :  
			return _MAP_LOCALHOST_API_KEY;
	}
}

function geLocalApiKey(){
switch(window.location.hostname){
		case 'dna.daum.net' :
			return _LOCAL_DNA_API_KEY;
		case 'dna.dev.daum.net' :
			return _LOCAL_DNADEV_API_KEY;
		default :  
			return _LOCAL_LOCALHOST_API_KEY;
	}
}

/*
 * 주소 검색을 위한 function
 * */
function searchPosition(id){
	var query = $("#" + id).val();

	if(query == null || query == "" || query == "주소검색"){
		alert("검색어를 입력해 주십시오");
		return;
	}
	pingSearch(query);
}

/*
 * 주소 검색을 위한 api 요청
 * */
function pingSearch(query){

	var oScript = document.createElement("script");
	oScript.type = "text/javascript";
	oScript.charset = "utf-8";

	oScript.src = addrToCoordApiAddr + _LOCAL_DNA_API_KEY + "&output=json&callback=" +  addrSearchCallback + "&q=" + encodeURI(query);
	//oScript.src = addrToCoordApiAddr + geLocalApiKey()  + "&output=json&callback=" + addrSearchCallback + "&q=" + encodeURI(query);
    document.getElementsByTagName("head")[0].appendChild(oScript);
}
/*
 * 주소 검색 callback method
 * */
function pongSearch(data){
	var resultForm = document.getElementById(searchResultBody);
	resultForm.innerHTML = "";

	if(!data.channel || data.channel.item.length <= 0){
		resultForm.innerHTML = "검색 결과가 없습니다.";
		return;
	}else{
		var i;
		for (i = 0; i < data.channel.item.length; i++){
			resultForm.innerHTML += "<div class='addrResult'><a href='javascript:searchMark(" + data.channel.item[i].lat + "," + data.channel.item[i].lng + "," + i + ")'>"
									+ strip_tag(data.channel.item[i].title) + "</div>"
									+ "<input type='hidden' id='localTitle_" + i + "' name='localTitle_" + i + "' value='" + data.channel.item[i].title + "'>"
									+ "<input type='hidden' id='localName1_" + i + "' name='localName1_" + i + "' value='" + data.channel.item[i].localName_1 + "'>"
									+ "<input type='hidden' id='localName2_" + i + "' name='localName2_" + i + "' value='" + data.channel.item[i].localName_2 + "'>"
									+ "<input type='hidden' id='localName3_" + i + "' name='localName3_" + i + "' value='" + data.channel.item[i].localName_3 + "'>"
									+"</div>";
		}
	}
}

function strip_tag (str){
	str = str.replace(/&lt;/g, '<').replace(/&gt;/g,'>');
	return str.replace(/(<([^>]+)>)/ig,"");
}

/*
 * 검색된 주소 클릭시 오른쪽 맵에 마커 표시
 * */
function searchMark(lat,lng,num){
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
	
	var localName1 = $("#localName1_" + num).val();
	var localName2 = $("#localName2_" + num).val();
	var localName3 = $("#localName3_" + num).val();
	var localTitle = $("#localTitle_" + num).val();
	
	// 검색결과를 클릭했을 때 좌표 찍히고, review_xxx 에 데이터 들어감.
	$("#review_latitude").val(lat);
	$("#review_longitude").val(lng);
	$("#review_localname1").val(localName1);
	$("#review_localname2").val(localName2);
	$("#review_localname3").val(localName3);
	$("#review_localtitle").val(localTitle);
	
}
/*
 * 좌표를 통해서 주소 가져오는 function
 * */
function addAddress(lat, lng){
	var oScript = document.createElement('script');
	oScript.type ='text/javascript';
	oScript.charset ='utf-8';
	
	oScript.src = coordToAddrApiAddr + _LOCAL_DNA_API_KEY +
	//	oScript.src = coordToAddrApiAddr + geLocalApiKey() + 
				  '&latitude=' + lat + '&longitude=' + lng +
				  '&output=json&callback=addrSearch';
    document.getElementsByTagName('head')[0].appendChild(oScript);
}

/*
 * 좌표 -> 주소검색 callback
 * */
function addrSearch(data){
	
	alert(data.fullName);
	
	if(!data || data.item.length <= 0){
//		resultForm.innerHTML = "검색 결과가 없습니다.";
		alert("!!");
		return;
	}else{
		alert("??");
	}
//	$("#" + roadViewAddrHeader).text(data.fullName);
}

/*
 * 줌레벨 세팅
 * 
 */
function setZoomLevel(level){
	for(var i = 0 ; i < document.getElementById('zoomForm').length;i++){
		if(document.getElementById('zoomForm').options[i].value == level){
			document.getElementById('zoomForm').selectedIndex = i;
		}
	}
}

/*
 * 맵에 줌 적용
 * */
function changeZoomLevel(level){
	map.setLevel(level.value);
}

/* preview Popup*/
var popupStatus = 0;
function showPreView(){
	if(!beforePreviewValid())
		return ;
	loadPreviewMap();
	setPreviewSize();
	centerPopup();
	loadPopup();
}

function loadPopup(){
	if(popupStatus==0){
		$("#backgroundPopup").css({
			"opacity": "0.7"
 		});
 		$("#backgroundPopup").fadeIn("slow");
 		$("#popupContact").fadeIn("slow");
    		popupStatus = 1;
  	}
}
function centerPopup(){
	  var windowWidth = document.documentElement.clientWidth;
	  var windowHeight = document.documentElement.clientHeight;
	  var popupHeight = $("#popupContact").height();
	  var popupWidth = $("#popupContact").width();
	  $("#popupContact").css({
	    "position": "absolute",
	    "top": windowHeight / 2 - popupHeight / 2,
	    "left": windowWidth / 2 - popupWidth / 2
	  });
	  $("#backgroundPopup").css({
	  	"height": windowHeight
	  });
	}
function disablePopup(){
	if(popupStatus==1){
		$("#backgroundPopup").fadeOut("slow");
		$("#popupContact").fadeOut("slow");
		popupStatus = 0;
	}
}

function setPreviewSize(){
	var width = document.getElementById("width").value; 
	var height = document.getElementById("height").value;


	$("#popupContact").width((parseInt(width) + 10) + "px");
	$("#popupContact").height((parseInt(height) + 10) + "px");

	$("#preMap").width(width + "px");
	$("#preMap").height(height + "px");
	
	$("#previewMap").width(width + "px");
	$("#previewMap").height(height + "px");
}
function loadPreviewMap(){
	var preMap = document.mapFrm;
	preMap.target = "previewMap";
	preMap.action = "preview_map.php";
	preMap.submit();
}
function beforePreviewValid(){		//팝업띄울때 valid한 값인지 체크
	var width = $("#width").val();
	var height = $("#height").val();

	if(width > 800){
		alert("미리보기 하실 수 없는 크기 입니다.\n미리보기의 최대 크기는 가로 800px, 세로 550px 입니다.");
		$("#width").val("");
		$("#width").focus();
		return false;
	}

	if(height > 550){
		alert("미리보기 하실 수 없는 크기 입니다.\n미리보기의 최대 크기는 가로 800px, 세로 550px 입니다.");
		$("#height").val("");
		 $("#height").focus();
		return false;
	}

	if(width == "" || height == ""){
		alert("크기를 입력해 주세요.");
		return false;
	}
	
	return true;
}

function numberCheck(loc) {				//숫자만 입력받게 함.
	if(/[^0123456789]/g.test(loc.value)) {
		alert("숫자만 입력해 주세요.");
		loc.value = "";
		loc.focus();
	}
}
function changeClass(form){
	form.setAttribute('class','sizeForm');
}

function overLayContents(e){
	if(infoWindow != null){
		infoWindow.close();
		infoWindow = null;
	}
	var text = "<div style='padding:5px;text-align:center;'>" + $("#contents").val().split("\n").join("<br/>") + "</div>"; 
	infoWindow = new daum.maps.InfoWindow({
		position : po,
		content : text,				
		removable : false
	});
	infoWindow.open(map, mark);
}

function gotoNextStep(){
	var width = $("#width").val();
	var height = $("#height").val();

	if(width == "" || height == ""){
		alert("지도 크기 입력은 필수 사항입니다.");
		return;
	}
	document.mapFrm.action = "step3.php";
	document.mapFrm.target = "_self";
	document.mapFrm.submit();
}

function rvSubmit(){
	var form = document.mapFrm;
	form.target = "rv_frame";                                             

	form.action = "step2_rv.php";                             
	form.submit();   
}
function hideRoadView(width, height){
	$("#map").css({
		width: width + 'px',
		height: height + 'px'
	});

	$("#rv").css({
		"width" : 0,
		"height" : 0
	});	
	
	$("#rv_frame").css({
		"width" : 0,
		"height" : 0
	});	
	$("#rvHeader").css({
		display : 'none'
	});
	$("#rvClose").css({
		display : 'none'
	});
	
}
function showRoadView(){

	$("#map").css({
		"width" : 0,
		"height" : 0
	});

	$("#rv").css({
		width: '540px',
		height: '414px'
	});
	$("#rv_frame").css({
		width: '540px',
		height: '400px'
	});
	$("#rvHeader").css({
		display : 'block',
		width: '534px'
	});
	$("#rvClose").css({
		display : 'block'
	});
}

/*
 * 프리뷰 창의 로드뷰 숨기기
 * */
function hidePreviewRoadView(width, height){
	$("#map").css({
		"width" : width + 'px',
		"height" : height + 'px'
	});

	$("#rv").css({
		"width" : 0,
		"height" : 0
	});	
	
	$("#rv_frame").css({
		"width" : 0,
		"height" : 0
	});	
	$("#rvHeader").css({
		display : 'none'
	});
	$("#rvClose").css({
		display : 'none'
	});
	
}

/*
 * 미리보기 창에서 로드뷰 보이기
 * */
function showPreviewRoadView(width, height){

	$("#map").css({
		"width" : 0,
		"height" : 0
	});

	$("#rv").css({
		"width" : width + 'px',
		"height" : height + 'px'
	});
	$("#rv_frame").css({
		"width" : width + 'px',
		"height" : (parseInt(height) - 20) + 'px'
	});
	$("#rvHeader").css({
		display : 'block',
		width: parseInt(width) - 5 + 'px'
	});
	$("#rvClose").css({
		display : 'block'
	});
}

/*
 * 미리보기 로드뷰 iframe으로 submit
 */
function rvPreviewSubmit(width, height, latitude, longitude){
	var form = document.rvFrm;                                       
	form.target = "rv_frame";                                             

	form.action = "preview_rv.php" + 
				  "?width=" + width + 
				  "&height=" + height + 
				  "&latitude=" + latitude + 
				  "&longitude=" + longitude;                                
	form.submit();   
}


/**
 * 
 * @param option 로드뷰로 넘겨주는 파라미터 값
 * @return
 */
function rvViewSubmit(option){
	var form = document.rvFrm;                                       
	form.target = "rv_frame";                                             

	form.action = "rv_view.php" + option;                       
	form.submit();   
}

function showDaumBi(target, width){
	$("#" + target).css({
		width  : width + "px",
		height : 40,
		"background-color": "#F7F7F7",
		border: "1px solid #E9E9E9"

	});
}
function extendPanel(target, width ,height){
	$("#" + target).css({
		width: width + "px",
		height: height + "px",
		margin : "0 auto"
	});
}

function showViewRoadView(width, height){
	$("#map").css({
		"width" : 0,
		"height" : 0
	});

	$("#rv").css({
		"width" : width + 'px',
		"height" : height + 'px'
	});
	$("#rv_frame").css({
		"width" : parseInt(width)  + 'px',
		"height" : (parseInt(height) - 35) + 'px'
	});
	$("#rvHeader").css({
		display : 'block',
		width: parseInt(width) + 'px'
	});
	$("#rvClose").css({
		display : 'block'
	});
}

/*
*  출발지 입력체크
*/
function searchRoute(id, lat, lng, eName)
{
	var sName = $("#" + id).val();
	if(sName == null || sName == "" || sName == "출발지 입력" || sName == "출발지입력"){
		alert("출발지를 입력해 주십시오");
		return;
	}
	showRoute(lat, lng, sName, eName);
}


/*
 *  길찾기 새창띄우기
 * */
function showRoute(lat, lng, sName, eName){
	clickCode("roughmap_routine");
	var urlQuery = "http://map.daum.net?map_type=TYPE_MAP$map_hybrid=false&sName=" + sName + "&eName=" + eName + "&eX="  + lng+ "&eY=" + lat;
	window.open(urlQuery);
}

// /*
// * 좌표변환을 위한 API 호출
// */
function convertCoord(lat, lng)
{
	var oScript = document.createElement("script");
	oScript.type = "text/javascript";
	oScript.charset = "utf-8";		
	oScript.src = "http://apis.daum.net/local/geo/transcoord?apikey=" + geLocalApiKey() 
	+ "&y=" + lat + "&x=" + lng + "&fromCoord=WGS84&toCoord=wcongnamul"
	+ "&output=json&callback=convertCoordCallback";
	document.getElementsByTagName("head")[0].appendChild(oScript);
}


var wcongnamulLatitude;
var wcongnamulLongitude;
/*
 * 좌표 검색 callback method
 * */
function convertCoordCallback(data){
	wcongnamulLongitude = data.x;
	wcongnamulLatitude = data.y;
}

/**
* 클릭코드 삽입
* 
*/
function clickCode(type)
{
	new Image().src="http://stlog1.local.daum.net/logcollector/log/map?type=" + type;
}