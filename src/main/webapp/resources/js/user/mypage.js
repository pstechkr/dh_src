
// 지도 불러오기
function mapLayer(lat,lng){
	var latitude = lat;
	var longitude = lng;
	
	var position = new daum.maps.LatLng(latitude, longitude);
	var map = new daum.maps.StaticMap(document.getElementById("map"), {
		center: position,
		level: 4,
		mapTypeId: daum.maps.MapTypeId.ROADMAP,
			marker: [
	                 	{
	                        position: position,
	                        /* text: '${gatheringInfoModel.gathering_place}' */
	                    }
	                ]
	});

}
// 지도 띄우기
function mapView(lat, lng){
	
	// map 비우기
	$("#map").empty();
	
	// mask width, height를 구함.
	var maskWidth = $(document).width();
	var maskHeight = $(document).height();
	$("#mask").css({"width":maskWidth, "height":maskHeight});
	
	// show
	$("#mask").fadeIn();
	
	// layer margin top 구함.
	var top = ($(window).scrollTop() + ($(window).height() - $("#layer").height())/2);
	$("#layer").css({"margin-top":top});
	
	mapLayer(lat, lng);
	
}


// 포인트 주기
function point(seq, product, point){
	
	// ajax response 때까지 중복클릭 방지.
	$("tr." + seq + "." + product +">td.review>button.point").attr("onclick","").unbind("click");
	
	var request = {};
	request["schedule_seq"] = seq;
	request["product"] = product;
	request["point"] = point;
	
	$.ajax({
		url: "pointRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
	    	if(response["isCheck"]){
				alert("평가에 참여해 주셔서 감사합니다.");
				location.reload(true);
			}else{
				alert("평가 반영에 실패하였습니다. 잠시 후 다시 시도해 주세요.");
				$("tr." + seq + "." + product +">td.review>button.like").attr("onclick","javascript:point('" + seq + "', '" + product + "', '" + point + "');").bind("click");
			}
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end

}

//예약취소
function rsvCancelRequest(seq, product){
	
	if(!confirm("정말 예약을 취소 하시겠습니까?")){
		return false;
	}
	
	// ajax response 때까지 중복클릭 방지.
	$("tr." + seq + "." + product +">td.review>button.rsv_cancel").attr("onclick","").unbind("click");
	
	var request = {};
	request["schedule_seq"] = seq;
	request["product"] = product;
	
	$.ajax({
		url: "rsvCancelRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
			
	    	if(response["isCheck"]){
				alert("예약이 취소 되었습니다.");
				location.reload(true);
				
			}else{
				alert("예약 취소를 실패하였습니다.");
				// 이벤트 추가.
				$("tr." + seq + "." + product +">td.review>button.rsv_cancel").attr("onclick","javascript:rsvCancelRequest('"+seq+"', '"+product+"');").bind("click");
			}
	    	
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
			// 이벤트 추가.
			$("tr." + seq + "." + product +">td.review>button.rsv_cancel").attr("onclick","javascript:rsvCancelRequest('"+seq+"', '"+product+"');").bind("click");
	    }
	}); // ajax end
}

// 결제 취소
function rsvConfirmCancelRequest(seq, product){
	
}


// 영수증
function receipt(key){
	
	switch (key){
	case "cash" : 
		alert("무통장입금 거래의 경우 결제완료 후 다음 날 국세청 현금영수증 홈페이지에서 확인하실 수 있습니다.");
		window.open("http://www.taxsave.go.kr","_blank");
	}
	
}
