function deleteGet(url){
	var _url = url.split("?");
	return _url[0];
}
// 끝에 slash가 붙어있으면 slash를 삭제한다. 
// key가 true이면 처음에 /와 / 사이에 있던 내용이 삭제된다. (상위폴더 이동과 같음)
function makeURL(url, key){
	
	// get붙어있는 경우 없애줌
	url = deleteGet(url);
	
	// "/" 삭제
	if(url.charAt(url.length - 1) == "/"){
		url = url.substring(0, url.length - 1); }
	
	// 한단계 위로
	if(key == true){
		url = url.substring(0, url.lastIndexOf("/")); }

	return url;
}


$(document).ready(function(){
	
	// 매장관리, 예약 및 상품관리 
	$("div.edit>dl").on({
		click: function (event){
			
			// active가 아닌 경우에 active로 변경하면서 안의 내용을 불러온다.
			if(!$(this).hasClass("active"))
			{
				// 예외 - reservation.jsp에서 예약날짜관리는 해당 클릭 이벤트가 적용되지 않도록 함.
				if(!$(this).hasClass("rsv_date")){
					
					// 다른 카드 클릭시 edit로 열려있던 다른 카드창을 닫음 & 다른 창의 active도 닫음 (new가 edit로 열려있던 경우도 존재)
					$("div.edit>dl").removeClass("active");
					$("#card_area.contents>.card").removeClass("edit");
					if($("#card_area.contents>.card").hasClass("new")){
						$("#card_area.contents>.new").remove(); $(".new_menu").show();
					}
					$(this).addClass("active");
				}
				
				// 중간에 선택자가 하나 더 있으면 페이지별로 묶을텐뎅
				var _key = $(this).attr("class").toString().substring(0, $(this).attr("class").toString().indexOf(" active"));				
				
				// 매장관리 페이지
				if(_key == "store_name"){editView("store_name");}
				else if(_key == "store_address"){loadMap();}
				else if(_key == "store_tel"){editView("store_contactnumber");}
				else if(_key == "store_hour"){editView("store_openinghours");}
				else if(_key == "store_breaktime"){editView("store_breaktime");}
				else if(_key == "store_close"){editView("store_dayoff");}
				else if(_key == "store_park"){editView("store_parkinginfo");}
				else if(_key == "store_intromessage"){editView("store_intromessage");}
				
				// 예약관리 페이지 
				else if(_key == "rsv_common"){editView("rsv_common");}
			}
		}
	});
	
	
	// 매장관리, 예약 및 상품관리 > 취소 버튼을 누르면, 해당 부모의 active를 제거한다.
	$('.edit .btn_cancel').on({ 
		click: function (event){

			event.stopPropagation(); // 캡처링 방지
			$(this).parents(".edit>dl").removeClass("active");
		}
	});
	
	
	
	
	
	// 예약상품관리 > 카드 관련 이벤트 - 카드를 동적으로 생성하므로 이벤트를 수정함
	$(document).on("click", ".card>.contents", function(){
		
		if(!$(this).parent().hasClass("edit")){
			
			// 다른 카드 클릭시 edit로 열려있던 다른 카드창을 닫음 & 다른 창의 active도 닫음 (new가 edit로 열려있던 경우도 존재)
			$("div.edit>dl").removeClass("active");
			$("#card_area.contents>.card").removeClass("edit");
			if($("#card_area.contents>.card").hasClass("new"))
			{ $("#card_area.contents>.new").remove(); $(".new_menu").show(); }
				
				
			$(this).parent().addClass("edit");
			// editView 호출해야징!
			editView("card");
		}
	});
	
	// 예약상품관리 > 카드 관련 : 취소버튼
	$(document).on("click", ".rsv_edit_cancel", function(){
		
		// 일반 card
		if(!$(this).parents(".card").hasClass("new")){
			$(this).parents(".card").removeClass("edit");
		}else{
			// 신규 등록버튼으로 생성된 card
			$(this).parents(".card").remove(); // 취소버튼 클릭시 동적생성되었던 card를 삭제.
			$(".new_menu").show();
		}
	});
	
	
	// 스케줄관리 > 달력 날짜 클릭 - 달력을 동적으로 생성하므로 이벤트를 수정함
	$(document).on("click", "#schdl_management #calendar td", function(){
		
		if(!$(this).hasClass("disable")){
			
			$("#schdl_management td").removeClass("selected");
			$(this).addClass("selected");
			
			var year = Number($("#calYear").html());
			var month = Number($("#calMonth").html());
			var date = Number($(this).children("span").html());
			
			// 시간별 예약자 명단 신청하는 ajax -> schedule.js
			timetableRequest(year, month, date);
			
			
		}else{
			return false();
		}
		
	});
	
	
	// 스케줄관리 > 타임테이블 클릭 - 타임테이블을 동적으로 생성하므로 이벤트를 붙여줌
	$(document).on("click", "#schdl_management #rsv_list td", function(){
		
		var time = $(this).parents().children("td:first-child").children("span").html();
		memberRequest(time);
	});
	
});