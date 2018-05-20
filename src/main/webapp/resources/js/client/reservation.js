$(document).ready(function(){
	calendarRequest("ready");
	viewCard("card");
	
	// 예약관리 > 예약날짜관리 > 달력날짜 선택 후 버튼
	$('#rsv_management .btn_cancel').on({
		
		click: function (event){
			$("#rsv_management td").removeClass("selected");
			$("#rsv_management .setting").hide();
		
		}
	});
	
	// 예약관리 > 달력 날짜 클릭 - 달력을 동적으로 생성하므로 이벤트를 수정함
	$(document).on("click", "#rsv_management td", function(){
		
		// disable(회색글씨)가 아닌 부분만 이벤트가 가능하도록 함.
		if(!$(this).hasClass("disable")){
			
			$("#rsv_management td").removeClass("selected");
			$(this).addClass("selected");
			
//			$("#rsv_management .message").hide();
			$("#rsv_management .setting").show();
			
			// reservation.js
			editView("rsv_calendar");
		
		}else{
			return false();
		}
		
	});
	
});

// 예약기능 ON/OFF
function rsvSwitch(num){
	switch(num){
		case 1 :
			$(function(){
				$(".rsv_switch button").removeClass("btn_active");
				$(".rsv_switch button").eq(0).addClass("btn_active");
				$("#sw_contents").show();
				$("#card_area").show();
			});
		break;
		case 2 :
			$(function(){
				$(".rsv_switch button").removeClass("btn_active");
				$(".rsv_switch button").eq(1).addClass("btn_active");
				$("#sw_contents").hide();
				$("#card_area").hide();
			});
		break;
		default :
			return false;
		break;
	}
}



// 캘린더를 요청하는 함수
function calendarRequest(calKey){
	
	var request = {};
	var year;
	var month;
	switch(calKey){
	
		case "ready" : 	
			break;
		
		case "prev"  :	
			year = Number($("#calYear").text());
			month = Number($("#calMonth").text()) -1;

			if(month < 1)
			{	month+12; year-1;	}
			
			request["year"] = year;
			request["month"] = month;
			break;
					
		case "next"  :
			year = Number($("#calYear").text());
			month = Number($("#calMonth").text()) +1;
			
			if(month > 12)
			{	month-12; year+1;	}
			
			request["year"] = year;
			request["month"] = month;
			break;
	}
	
	$.ajax({
		url: "calendarRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
			
			yearmonthText = "<span id='calYear'>" + response[0] + "</span>.<span id='calMonth'>" + response[1] + "</span>";
			
	    	var week = new Array();
	    	var calText = "";
	    	for(var i=0; i < response[2].length; i++)
	    	{ 
	    		// 받아온 JSON값을 주간으로 자른다.
	    		week[i] = eval(response[2][i]);
	    		
	    		// 여기에서 tr을 하나 추가해야한다.
	    		calText += "<tr>";
	  
	    		// 해당 주를 다시 날짜별로 자른다.
	    		for (var j = 0; j < 7; j++ )
	    		{
	    			var day = eval(week[i]);
	    			
	    			// *** 여기에서 td class를 변경하면 된다. ***
	    			// 만일 오늘 이전이라면, 버튼을 비활성화한다.
	    			
		    			if(day[j].this_MONTH){
		    				if(day[j].reservation_SWITCH) { calText += "<td><span>" + day[j].date + "</span></td>"; } // 예약가능일
		    				else { calText += "<td class='reserved'><span>" + day[j].date + "</span></td>"; } // 예약불가일
		    			}else { calText += "<td class='disable'><span>" + day[j].date + "</span></td>"; } // 지난달 혹은 다음달
	    		}
	    	}
	    	
	    	$(".date_area").html(yearmonthText);
	    	$(".calendar_load").html(calText);
	
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end
} // function end


// 예약기능 > 예약기본정보 눌렀을 때 View 정보를 활용하여 데이터값을 받아온다.
function editView(key){

	if(key == "rsv_common")
	{
		// 데이터 불러온 뒤 예약시간, 인원 value 고정
		var _key = "#view_rsv_openinghours";
		var _id = "#rsv_openinghours";
		
		var split1 = $(_key + "1").text().split(":");
		var split2 = $(_key + "2").text().split(":");
				
		$(_id + "1").val(split1[0]);
		$(_id + "2").val(split1[1]);
		$(_id + "3").val(split2[0]);
		$(_id + "4").val(split2[1]);
		
		// 인원
		var _keyMax = $("#view_rsv_max").text();	
		var _idMax = "#rsv_max";
		$(_idMax).val(_keyMax);
		
	}else if(key =="rsv_calendar"){
		
		var syear = $("#calYear").text();
		var smonth = $("#calMonth").text();
		var sdate =$(".selected > span").text();
		
		if(Number(smonth) < 10){
			smonth = "0" + Number(smonth);}
		else{
			Number(smonth);}
			
		if(Number(sdate) < 10){
		sdate = "0" + Number(sdate);}
		else{
			Number(sdate);}
		
		// setting 상단의 날짜 표시
		var YMD = syear + ". " + smonth + ". " + sdate;
		$(".setting > span").html(YMD);
		$("input:radio[name='rsv_setting']").removeAttr("checked");
		
		//radio 버튼 체크
		if($(".selected").hasClass("reserved")){
			$("input:radio[name='rsv_setting']").eq(1).prop("checked",true);
		}else{
			$("input:radio[name='rsv_setting']").eq(0).prop("checked",true);
		}
	}
	
	// edit에 내용을 담아준다. (default.js에서 이동)
	else if(key == "card"){
		
		var splitCode = $("#card_area.contents>.edit").attr("class").split(" ");
		var thisCard = "";
		// cnt값 찾기
		for(var i = 0 ; i < splitCode.length; i++){
			// rtn이 -1이 아니면 cnt값이 들어있다는 뜻 
			if(splitCode[i].indexOf("cnt") != -1){
				thisCard = splitCode[i];}}
		/*
		 * 상품명
		 * 가격
		 * 소요시간(input hidden)
		 * 예약가능요일(input hidden)
		 * 상품설명
		 * 
		 */
		var menuName = $("#view_menu_name_" + thisCard).text();
		var menuPrice = $("#view_menu_price_" + thisCard).text();
		var menuTime = $("#view_menu_time_hidden_" + thisCard).val().substring(0, 2);
		var menuDay = $("#view_menu_day_hidden_" + thisCard).val().split("/");
		var menuInstruction = $("#view_menu_instruction_" + thisCard).text();
		
		// 각 카드를 클릭시 view에서 반영됨
		$("#menu_name_"+thisCard).val(menuName);
		$("#menu_price_"+thisCard).val(menuPrice);
		$("#menu_instruction_"+thisCard).val(menuInstruction);
		
		var index = 0;
		$("input[name=menu_day_" + thisCard + "]").each(function(){  // edit에 반영.
			if(menuDay[index] == 1)
			{ $(this).prop("checked", true); }
			index ++; });
		
		menuTime = Number(menuTime); // 숫자로 변경해서 앞자리의 0을 없앤다.
		$("select[name=menu_time_" + thisCard + "]").val(menuTime); // edit에 반영.
		
	}	
}

// 수정
function modify(key){
	var request = {};
	// 예약 기본 정보 수정
	// 원래 내용과 바뀐 것을 찾아서 request에 담아 보낸다.
	if(key == "rsv_common"){
		
		// reservation hours1
		var editOpeninghours1 = $("#rsv_openinghours1").val() + ":" + $("#rsv_openinghours2").val();
		var viewOpeninghours1 = $("#view_rsv_openinghours1").text();
		// reservation hours2
		var editOpeninghours2 = $("#rsv_openinghours3").val() + ":" + $("#rsv_openinghours4").val();
		var viewOpeninghours2 = $("#view_rsv_openinghours2").text();
		
		if(editOpeninghours1 != viewOpeninghours1 || editOpeninghours2 != viewOpeninghours2){
			request["reservation_openinghours1"] = editOpeninghours1;
			request["reservation_openinghours2"] = editOpeninghours2;
		}
		
		// reservation_max
		var editMax = $("#rsv_max").val();
		var viewMax = $("#view_rsv_max").text();
			
		if(editMax != viewMax)
			request["reservation_max"] = editMax;
	}
	
	// 변경사항이 없다.
	if(JSON.stringify(request).length <= 2)
	{	alert("변경 사항이 존재하지 않습니다!");
		return;	}
	
	// ajax
	$.ajax({
		url: "modify",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){

        	if(response["reservation_openinghours1"] != null && response["reservation_openinghours2"] != null)
        	{
        		$("#view_rsv_openinghours1").html(response["reservation_openinghours1"]);
        		$("#view_rsv_openinghours2").html(response["reservation_openinghours2"]);
        		
        	}
        	
        	if(response["reservation_max"] != null)
        	{
        		$("#view_rsv_max").html(response["reservation_max"]);
        	}
        	
        	alert("변경 사항이 적용되었습니다.");
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}// function end



// 예약 날짜 관리 수정
function modifyDate(){
	var request = {};
	
	var year = $("#calYear").text();
	var smonth = $("#calMonth").text();
	var sdate =$(".selected > span").text();
	var month = Number(smonth);
	var date = Number(sdate);
	if(Number(smonth) < 10){
		smonth = "0" + Number(smonth);}
	else{
		Number(smonth);
	}
	if(Number(sdate) < 10){
	sdate = "0" + Number(sdate);}
	else{
		Number(sdate);}	
	
	var YMD = year + "-" + smonth + "-" + sdate;  
	var value = $(":radio[name=rsv_setting]:checked").val();

	// 예약불가능 상태에서 예약불가능을 신청하려고 할 때이거나 예약 가능 상태에서 예약 가능을 신청하려고 할 때 
	if(($(".selected").hasClass("reserved") && value == 0) || (!$(".selected").hasClass("reserved") && value == 1)){
		alert("변경 사항이 없습니다!");
		return;
	}
	
	
	request["custom_rsv_ymd"] = YMD;
	request["custom_rsv_year"] = year;
	request["custom_rsv_month"] = month;
	request["custom_rsv_date"] = date;
	request["custom_rsv_switch"] = value;
	
	alert(JSON.stringify(request));
	
	
	// ajax
	$.ajax({
		url: "reservationSwitch",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){

        	alert("변경 사항이 적용되었습니다.");
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}// function end
