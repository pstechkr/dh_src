// ready
$(document).ready(function(){
	editView("all");
});

// 맵 로드는 default 쪽에서, 화면이 변경되었을 때 실행하도록.
function loadMap(){
	
	var lng = $("#store_longitude").val();
	var lat = $("#store_latitude").val();
	
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
};

// 수정된 화면 반영
function editView(key){
	
	if(key == "store_dayoff"){
		// 데이터 불러온 뒤 정기휴무일 수정란에 check 및 날짜로 변경하여 보여주기 
		var splitCode = $("#view_store_dayoff_hidden").val().split("/");
		var index = 0;
		var viewCode = "";
		$("input[name=store_dayoff]").each(function(){
			if(splitCode[index] == 1)
			{	
				$(this).prop("checked", true);
				viewCode += $(this).val() + ", ";
			}
			index ++;
		});
		viewCode = viewCode.substring(0, viewCode.length -2);
		$("#view_store_dayoff").html(viewCode);
	}
	else if(key == "store_openinghours" || key == "store_breaktime"){
		// 데이터 불러온 뒤 영업시간 및 브레이크 타임 수정란에 value 고정하기
		var _key = "#view_" + key;
		var _id = "#" + key.substring(key.indexOf("_")+1, key.length);
		
		var split1 = $(_key + "1").text().split(":");
		var split2 = $(_key + "2").text().split(":");
		
		$(_id + "1").val(split1[0]);
		$(_id + "2").val(split1[1]);
		$(_id + "3").val(split2[0]);
		$(_id + "4").val(split2[1]);
	}
	else if(key == "store_contactnumber")
	{
		// 데이터 불러온 뒤 연락처 수정란에 value 고정하기
		var splitCode = $("#view_store_contactnumber").text().split("-");
		$("#store_contactnumber1").val(splitCode[0]);
		$("#store_contactnumber2").val(splitCode[1]);
		$("#store_contactnumber3").val(splitCode[2]);
	}
	// textarea <br> 변경 때문에.
	else if(key == "store_parkinginfo" || key == "store_intromessage")
	{
		var _key = "#view_" + key;
		var viewCode = $(_key).html();
		viewCode = viewCode.replace(/<br>/gi, "\r\n");
		$("#" + key).val(viewCode);
	}
	else{
		if(key == "all")
		{
			editView("store_dayoff");
			editView("store_openinghours");
			editView("store_breaktime");
			editView("store_contactnumber");
			editView("store_name");
			editView("store_parkinginfo");
			editView("store_intromessage");
		}
		else{
			var _key = "#view_" + key;
			var viewCode = $(_key).text();
			$("#" + key).val(viewCode);
		}
	}
}


// 비교하고 변한게 없으면 return으로 변경해야 함 (수정필요)
function modify(key){
	
	var request = {};
	
	// store_contactnumber
	if(key == "store_contactnumber"){
		request["store_contactnumber"] = $(store_contactnumber1).val() + "-" + $(store_contactnumber2).val() + "-" + $(store_contactnumber3).val();
	}
	
	// openinghours && breaktime
	else if(key == "store_openinghours" || key == "store_breaktime"){
		
		var _selector = "#" + key.split("_")[1].toString();
		request[key + "1"] = $(_selector + "1").val() + ":" + $(_selector + "2").val();
		request[key + "2"] = $(_selector + "3").val() + ":" + $(_selector + "4").val();
	}
	
	// store_dayoff
	else if(key == "store_dayoff")
	{
		var _dayoff = "";
		$("input[name=" + key + "]").each(function(){
			if($(this).is(":checked")){
				_dayoff += "1";	}
			else{
				_dayoff += "0";	}
			_dayoff += "/";
		});
		// 마지막 구분자는 없애고 request.
		_dayoff = _dayoff.substring(0, _dayoff.length -1);
		request[key] = _dayoff;
	}
	
	// store_address
	else if(key == "store_address")
	{
		var longitude = $("#store_longitude").val();
		var latitude = $("#store_latitude").val();
		var localtitle = $("#store_localtitle").val();
		var localname1 = $("#store_localname1").val();
		var localname2 = $("#store_localname2").val();
		var localname3 = $("#store_localname3").val();
		var localname4 = $("#store_localname4").val();
		
		if(latitude == "" || longitude == ""){
			alert("새로운 주소지를 등록해 주십시오.");
			return false;
		}
		// 주소 위경도
		request["store_longitude"] = longitude;
		request["store_latitude"] = latitude;
		// 주소 텍스트
		request["store_localtitle"] = localtitle;
		request["store_localname1"] = localname1;
		request["store_localname2"] = localname2;
		request["store_localname3"] = localname3;
		request["store_localname4"] = localname4;
		
	}
	
	// default
	else{
		
		var context = $("#"+key).val();
		context = context.replace(/\n/gi, "<br>");

		request[key] = context;
	}
	
	// 전송값 확인
	alert(JSON.stringify(request));
	
	
	// ajax
	$.ajax({
		url: "modify",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
        success:function(response){

        	var viewInnerHTML = "";
       		viewInnerHTML = "#view_" + key;
       		
       		// update 성공 여부 체크.
       		if(response["modifyYN"] == "N"){
       			alert("정보 업데이트에 실패하였습니다.. 관리자에게 문의 바랍니다.");
       			return false;
       		}
       		
       		// 성공시 화면에 변경사항을 바꾸어 준다.
       		if(key == "store_address"){
       			$(viewInnerHTML).html(response["store_localtitle"] + " " + response["store_localname4"]);
       		}
       		
       		else if(key == "store_dayoff"){
				$("#view_store_dayoff_hidden").val(response[key]);
				editView(key);

        	}
        	else if(key == "store_openinghours" || key == "store_breaktime"){
        		$(viewInnerHTML +"1").html(response[key+"1"]);
        		$(viewInnerHTML +"2").html(response[key+"2"]);
        	}
        	else{
        		$(viewInnerHTML).html(response[key]);
        	}

        	// active 풀기
        	$("div.edit>dl").each(function(){
        		if($(this).hasClass("active")){
        			$(this).removeClass("active");
        		}
        	});
        	
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}// function end
