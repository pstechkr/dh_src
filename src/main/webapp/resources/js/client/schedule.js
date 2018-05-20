
// 캘린더 클릭하여 타임테이블 리퀘스트
function timetableRequest(year, month, date){
	
	var request = {};
	
	
	request["schedule_year"] = year;
	request["schedule_month"] = month;
	request["schedule_date"] = date;
	
	// ajax
	$.ajax({
		url: "timetableRequest",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){
        	
        	var timetableText = "";
        	var scheduleSeqText = "";
        	for(var i=0; i < response["tableList"].length ; i++){
		    	timetableText += "<tr><td><span>" + response["tableList"][i]["time"] + "</span></td>"
		    						+ "<td><span>" + response["tableList"][i]["readyInt"] + "</span>명 / <span>" + response["tableList"][i]["doingInt"] + "</span>명</td></tr>";
		    	scheduleSeqText += "<input type='hidden' id='ready_" + response["tableList"][i]["time"] + "' value='" + response["tableList"][i]["ready"] + "' >"
		    						+ "<input type='hidden' id='doing_" + response["tableList"][i]["time"] + "' value='" + response["tableList"][i]["doing"] + "' >";
        	}
        	$("#timetable").html(timetableText);
        	$("#temp").html(scheduleSeqText);
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
	
}

// 타임테이블 클릭하여 명단 리퀘스트
function memberRequest(time){
	
	var request = {};
	alert(time);
	
	// : 를 인식하기 위해서는 : 앞에 \\를 넣어주어야 함.
	time = time.replace(":", "\\:");
	var ready = $("#ready_"+time).val();
	var doing = $("#doing_"+time).val();
	
	
	if(ready != "null"){
		request["ready"] = ready.slice(0,-1);
	}
	if(doing != "null"){
		request["doing"] = doing.slice(0,-1);
	}
	
	// ajax
	$.ajax({
		url: "memberRequest",
        type :"POST",
        contentType : "application/json;charset=UTF-8",
       	data:  JSON.stringify(request),
        success:function(response){
        	var readyMemberText = "";
        	var doingMemberText = "";
        	if(response["readyMemberList"] != null){
	        	for(var i=0; i < response["readyMemberList"].length ; i++){
	        	readyMemberText += "<tr>"
									+ "<th scope='row'><button>이 아이템을 제거합니다.</button></th>"
									+ "<td><span>11:00 ~ 12:00</span></td>"
									+ "<td><span>" + response["readyMemberList"][i]["menu_name"] + "</span></td>"
									+ "<td><span>" + response["readyMemberList"][i]["user_name"] + "</span></td>"
									+ "<td><span>" + response["readyMemberList"][i]["member"] + "</span></td>"
									+ "<td><span>" + response["readyMemberList"][i]["user_phoneno"] + "</span></td>"
									+ "<td><span>" + response["readyMemberList"][i]["account_yn"] + "</span></td>"
									+ "</tr>";
	        	}
        	}
        	// 공통 마지막
        		readyMemberText += "<tr class='edit_item'>"
									+ "<th scope='row'><button>이 아이템을 제거합니다.</button></th>"
									+ "<td><span>11:00 ~ 12:00</span></td>"
									+ "<td>"
									+	"<select class='item_name' name='' id=''>"
									+		"<option value=''>아이템 테스트</option>"	
									+	"</select>"
									+ "</td>"
									+ "<td>"
									+	"<input type='text' size='10' class='user_name'>"
									+ "</td>"
									+ "<td>"
									+	"<select name='' id='' class='user_number'>"
									+		"<option value='1'>1</option>"
									+		"<option value='2'>2</option>"
									+		"<option value='3'>3</option>"
									+		"<option value='4'>4</option>"
									+		"<option value='5'>5</option>"
									+		"<option value='6'>6</option>"
									+		"<option value='7'>7</option>"
									+		"<option value='8'>8</option>"
									+		"<option value='9'>9</option>"
									+		"<option value='10'>10</option>"
									+	"</select>"
									+ "</td>"
									+ "<td>"
									+	"<input type='text' class='user_tel'>"
									+ "</td>"
									+ "<td>"
									+	"<input type='text' class='state'>"
									+ "</td>"
									+"</tr>";
        	$("#readyList").html(readyMemberText);
        	
        	if(response["doingMemberList"] != null){
        		for(var i=0; i < response["doingMemberList"].length ; i++){
	        	doingMemberText += "<tr>"
									+ "<th scope='row'><button>이 아이템을 제거합니다.</button></th>"
									+ "<td><span>11:00 ~ 12:00</span></td>"
									+ "<td><span>" + response["doingMemberList"][i]["menu_name"] + "</span></td>"
									+ "<td><span>" + response["doingMemberList"][i]["user_name"] + "</span></td>"
									+ "<td><span>" + response["doingMemberList"][i]["member"] + "</span></td>"
									+ "<td><span>" + response["doingMemberList"][i]["user_phoneno"] + "</span></td>"
									+ "<td><span>" + response["doingMemberList"][i]["account_yn"] + "</span></td>"
									+ "</tr>";
	        	}
        	}
        	$("#doingList").html(doingMemberText);
        	
		}, // success end
        error: function(){
			alert("error! 관리자에게 문의하세요.");
        }
	}); // ajax end
	
}