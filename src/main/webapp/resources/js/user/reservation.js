/* 
 * 유저 쪽 상품예약 관련 js파일.
 * Copyright(c) Jihan Jang. (TIEIN Communications) 
 */

$(document).ready(function(){
	
	// 상품 선택 클릭
	$("#store_reservation .contents .menu_box dl").on({
		
		click: function(){
			
			// 상품선택에서 클릭한 부분이 active가 아닌 경우
			if(!$(this).hasClass("active")){
				
				// 상품선택에서 클릭한 부분을 active로 변경
				$(this).parent().children("dl").removeClass("active");
				$(this).addClass("active");

				// 달력에 있는 selected도 전부 제거. [아래 예약부분 초기화]
				$("#store_reservation .contents #calendar .table_layout table tbody tr td").removeClass("selected");
				$(".btn_rsv").removeClass("active");
				
				// 클릭한 부분의 menu_seq 기억하기 위해 input에 담아둠.
				$("#menuID").val($(this).attr("class").substring(2,$(this).attr("class").indexOf(" ")));
				
				// 상품설명 올리기. 내리기.
				// 이미 active 상태인 경우 올렸다가 내용을 바꾸고 내리기.
				if($(".menu_instruction").hasClass("active")){
					$(".menu_instruction").slideUp(function(){
						// 글내용 바꾸고, 내리기
						$(".menu_instruction span").removeClass("active");
						$(".menu_instruction span.instruction_"+ $("#menuID").val()).addClass("active");
						$(".menu_instruction").slideDown();
					});
				}else{
					// active 상태 아닌 경우 active 상태로 만들고,
					// 안에 글내용 바꾸고, 내리기
					$(".menu_instruction").addClass("active");
					$(".menu_instruction span.instruction_"+ $("#menuID").val()).addClass("active");
					$(".menu_instruction").slideDown();
				}
			}

			// 메뉴캘린더 로드
			calendarRequest("ready");
			
		}
	});
	
	// 예약하기 > 달력 날짜 클릭 - 달력을 동적으로 생성하므로 이벤트를 수정함
	$(document).on("click", "#store_reservation .contents #calendar .table_layout table tbody tr td", function(){
		
		if(!$(this).hasClass("disable") && !$(this).hasClass("reserved")){
			
			if($("#menuID").val() == ""){
				alert("상품을 선택해 주십시오.");
				return false;
			}
			
			$("#store_reservation .contents #calendar .table_layout table tbody tr td").removeClass("selected");
			$(this).addClass("selected");
			$(".btn_rsv").removeClass("active");
			var year = Number($("#calYear").html());
			var month = Number($("#calMonth").html());
			var date = Number($(this).children("span").html());
			
			$("#rsv_member .timetable_date").text(year + "." + month + "." + date);
			
			// 시간별 예약자 명단 신청하는 ajax
			timetableRequest(year, month, date);
			
		}else{
			return false();
		}
		
	});
	
	// 인원 변경시 selected가 있으면, 해당 날짜에 대한 timetableRequest 함. 
	$(document).on("change", "#reservation_member", function(){
	
		if($("#store_reservation .contents #calendar .table_layout table tbody tr td").hasClass("selected")){
			$(".btn_rsv").removeClass("active");
			var year = Number($("#calYear").html());
			var month = Number($("#calMonth").html());
			var date = $("#store_reservation .contents #calendar .table_layout table tbody tr td.selected>span").html();
			timetableRequest(year, month, date);
			
		}
	});
	
	// timetable 클릭 - 예약하기버튼 활성화, 총비용 체크.
	$(document).on("click", "#store_reservation .contents #rsv_list table tbody tr", function(){
		
		if(!$(this).hasClass("disabled")){
		
			// 클릭한 timetable 활성화 
			$("#store_reservation .contents #rsv_list table tbody tr").removeClass("active");
			$(this).addClass("active");
			
			// 예약버튼 활성화
			$(".btn_rsv").addClass("active");
		}
	});
});

// 캘린더를 요청하는 함수
function calendarRequest(calKey){
	
	var request = {};
	var year;
	var month;
	var seq = $("#menuID").val();
	request["menu_seq"] = seq;
	
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
		url: "menuCalendarRequest",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
	    
	    	/*
			 * 캘린더 로드
			 */
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


//캘린더 클릭하여 타임테이블 리퀘스트
function timetableRequest(year, month, date){
	
	var request = {};
	
	request["schedule_year"] = year;
	request["schedule_month"] = month;
	request["schedule_date"] = date;
	request["menu_seq"] = $("#menuID").val();
	request["member"] = $("#reservation_member").val();
	
	// ajax
	$.ajax({
		url: "timetableRequest",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){

        	var timetableText = "";
        	for(var i=0; i < response["tableList"].length ; i++){
        		
        		
        		if(response["tableList"][i]["reservation"]){
        			timetableText += "<tr class='possible'><td>";
        			timetableText += "<span class='time'>";
        		}else{
        			timetableText += "<tr class='disabled'><td>";
        			timetableText += "<span class='time'>";
        		}
    			timetableText += response["tableList"][i]["time"] + "</span></td>";
    			
	    		timetableText += "<td><span>" + response["tableList"][i]["readyInt"] + "</span>명 / <span>"
	    						+ response["tableList"][i]["doingInt"] + "</span>명</td></tr>";
		    	
        	}
        	$(".timetable").html(timetableText);
        	
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}


// 예약하기 버튼 클릭.
function reservationRequest(){
	
	// active가 아니면 return false;
	if(!$("#store_reservation .contents .calendar_wrap #rsv_timetable .btn_box dl dd button.btn_rsv").hasClass("active")){
		alert("상품과 날짜, 시간을 올바르게 선택해 주세요.");
		return false;
	}
	
	// ajax. 로그인 했는지 안했는지 체크하여 안한 경우 로그인 팝업창이 뜨도록 한다.
	$.ajax({
		url: "loginSessionCheck",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
        success:function(response){
        	
        	if(response["sessionCheck"] == "Y"){
        		
	        	// 로그인 성공의 경우 폼을 생성하여 post.
	        	var year = Number($("#calYear").text());
	        	var month = Number($("#calMonth").text());
	        	var date = $("#store_reservation .contents #calendar .table_layout table tbody tr td.selected>span").html();
	        	var menu = $("#menuID").val();
	        	var member = $("#reservation_member").val();
	        	var time = $(".timetable tr.active td .time").text();
	        	
	        	var ymd_year = String(year);
	        	var ymd_month = "";
	        	if(Number(month) < 10){ymd_month = "0" + String(Number(month));}else{ymd_month = String(month);}
	        	var ymd_date = "";
	        	if(Number(date) < 10){ymd_date = "0" + String(Number(date));}else{ymd_date = String(date);}
	        	var ymd = ymd_year + "-" + ymd_month + "-" + ymd_date; 
	        	
	        	var _time = time.split(":");
	        	var schedule_hour = _time[0];
	        	var schedule_min = _time[1];
	        	
	        	var $form = $("<form></form>");
	        	
	        	// confirmRequest
	        	$form.attr("action", "confirmRequest");
	        	$form.attr("method", "post");
	        	$form.appendTo("body");
	        	
	        	req_ymd = $("<input type='hidden' name='schedule_ymd' value='" + ymd + "'>");
	        	req_year = $("<input type='hidden' name='schedule_year' value='" + year + "'>");
	        	req_month = $("<input type='hidden' name='schedule_month' value='" + month + "'>");	
	        	req_date = $("<input type='hidden' name='schedule_date' value='" + date + "'>");
	        	
	        	req_time = $("<input type='hidden' name='schedule_hm' value='" + time + "'>");
	        	req_hour = $("<input type='hidden' name='schedule_hour' value='" + schedule_hour + "'>");
	        	req_min = $("<input type='hidden' name='schedule_min' value='" + schedule_min + "'>");
	        	
	        	req_menu = $("<input type='hidden' name='menu_seq' value='" + menu + "'>");
	        	req_member = $("<input type='hidden' name='member' value='" + member + "'>");
	        	
	        	
	        	$form.append(req_ymd).append(req_year).append(req_month).append(req_date).append(req_time).append(req_hour).append(req_min).append(req_menu).append(req_member);
	        	$form.submit();
        	}else{
        		
        		// 로그인 안된 경우.
        		
        		// mask width, height를 구함.
        		var maskWidth = $(document).width();
        		var maskHeight = $(document).height();
        		$("#mask").css({"width":maskWidth, "height":maskHeight});
        		
        		// show
        		$("#mask").fadeIn();
        		
        		// layer margin top 구함.
        		var top = ($(window).scrollTop() + ($(window).height() - $("#layer").height())/2);
        		$("#layer").css({"margin-top":top});

        	}
        }, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
}

