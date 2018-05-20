// 새로운 상품 등록하기 버튼을 클릭했을 때 해당 버튼 상단에 새로운 상품카드가 생성된다. 몇가지 다른 부분이 존재함
function new_menu(){

	// 다른 카드 클릭시 edit로 열려있던 다른 카드창을 닫음 & 다른 창의 active도 닫음 (new가 edit로 열려있던 경우도 존재)
	$("div.edit>dl").removeClass("active");
	$("#card_area.contents>.card").removeClass("edit");

	var newCnt;
	if($("#card_area.contents").children().size() > 0){
		// 자식의 마지막 노드의 바로 앞이 마지막 카드이고, 이를 찾아서 새로 만드는 cnt를 구할 수 있다. (마지막 노드는 새로 만드는 버튼)
		var splitCode = $("#card_area.contents").children().last().prev().attr("class").split(" ");
		var lastCard = "";
		// cnt값 찾기
		for(var i = 0 ; i < splitCode.length; i++){
			// rtn이 -1이 아니면 cnt값이 들어있다는 뜻 
			if(splitCode[i].indexOf("cnt") != -1){
				lastCard = splitCode[i].substring(3,splitCode[i].length);}}
		
		// 마지막 cnt를 찾았다.
		newCnt = Number(lastCard)+1;
	}else{
		newCnt = 1;
	}

	// 메뉴 버튼 위에 새로운 카드를 만든다.
	$(".new_menu").before(
		"<div class='card edit cnt" + newCnt + " new'>"
		+	"<input type='hidden' id='menu_seq_cnt" + newCnt + "' value=''>"
		+	"<div class='contents'>"
			+	"<div class='two_column'>"
				+	"<dl class='subject'>"
					+	"<dt>상품명</dt>"
					+	"<dd>"
						+	"<div class='list'><span id='view_menu_name_cnt"+ newCnt +"'></span></div>"
						+	"<div class='edit'><input type='text' id='menu_name_cnt" + newCnt + "'></div>"
					+	"</dd>"
				+	"</dl>"
				+	"<dl class='price'>"
					+	"<dt>가격</dt>"
					+	"<dd>"
						+	"<div class='list'><span id='view_menu_price_cnt"+ newCnt +"'></span> 원</div>"
						+	"<div class='edit'><input type='text' id='menu_price_cnt" + newCnt + "'> 원</div>"
					+	"</dd>"
				+	"</dl>"
				+	"<dl class='time'>"
					+	"<dt>소요시간</dt>"
					+	"<dd>"
						+	"<div class='list'><span></span> 시간</div>"
						+   "<input type='hidden' id='view_menu_time_hidden_cnt" + newCnt + "' value=''>"
						+	"<div class='edit'>"
							+	"<select name='menu_time_cnt" + newCnt + "' id='id_menu_time_cnt"+ newCnt + "'>"
								+	"<option value='1'>1</option>"
								+	"<option value='2'>2</option>"
								+	"<option value='3'>3</option>"
								+	"<option value='4'>4</option>"
								+	"<option value='5'>5</option>"
								+	"<option value='6'>6</option>"
								+	"<option value='7'>7</option>"
								+	"<option value='8'>8</option>"
								+	"<option value='9'>9</option>"
								+	"<option value='10'>10</option>"
								+	"<option value='11'>11</option>"
								+	"<option value='12'>12</option>"
								+	"<option value='13'>13</option>"
								+	"<option value='14'>14</option>"
								+	"<option value='15'>15</option>"
								+	"<option value='16'>16</option>"
								+	"<option value='17'>17</option>"
								+	"<option value='18'>18</option>"
								+	"<option value='19'>19</option>"
								+	"<option value='20'>20</option>"
								+	"<option value='21'>21</option>"
								+	"<option value='22'>22</option>"
								+	"<option value='23'>23</option>"
								+	"<option value='24'>24</option>"
							+	"</select>"
							+	 "시간"
						+	"</div>"
					+	"</dd>"
				+	"</dl>"
				+	"<dl class='day'>"
					+	"<dt>예약 가능 요일</dt>"
					+	"<dd>"
						+	"<div class='list'>"
						+	"<span id='view_menu_day_cnt" + newCnt + "'></span>"
						+   "<input type='hidden' id='view_menu_day_hidden_cnt" + newCnt + "' value=''>"
						+	"</div>"
						+	"<div class='edit'>"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='일'>일"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='월'>월"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='화'>화"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='수'>수"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='목'>목"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='금'>금"
							+	"<input type='checkbox' name='menu_day_cnt" + newCnt + "' value='토'>토"
						+	"</div>"
					+	"</dd>"
				+	"</dl>"
			+	"</div>"
			+	"<div class='one_column'>"
				+	"<dl class='description'>"
					+	"<dt>상품 설명</dt>"
					+	"<dd>"
						+	"<div class='list'>"
							+	"<span id='view_menu_instruction_cnt" + newCnt + "'></span>"
						+	"</div>"
						+	"<div class='edit'>"
							+	"<textarea id='menu_instruction_cnt" + newCnt + "' cols='85' rows='5'></textarea>"
						+	"</div>"
					+	"</dd>"
				+	"</dl>"
			+	"</div>"
		+	"</div>"
		+	"<div class='btn_box'>"
			+	"<button class='btn_save' onclick='createMenu(" + newCnt  +")'>저장</button>"
			+	"<button class='rsv_edit_cancel'>취소</button>"
		+	"</div>"
	+	"</div>"
	);
	
	// 신규 등록 버튼을 감춘다.
	$(".new_menu").hide();
}



// data를 불러온 것 중에서 소요시간, 예약가능요일은 db내용과 보여주는 내용이 다르므로 알맞게 변경해야 함.
function viewCard(){
	
	var cnt = $(".card").size(); // card 갯수
	var dayId = "#view_menu_day_cnt"; // day id
	var splitCodeId = "#view_menu_day_hidden_cnt"; // splitCode id
	var timeId = "#view_menu_time_cnt"; // time id
	var timeHidden = "#view_menu_time_hidden_cnt"; // time hidden id

	// 각 카드별 시행
	for(var i = 1 ; i <= cnt ; i++)
	{
		var num = i.toString();
		
		// 요일
		var splitCode = $(splitCodeId + num).val().split("/");
		var index = 0;
		var viewCode = "";
		$("input[name=menu_day_cnt" + num + "]").each(function(){  // edit에 반영.
			if(splitCode[index] == 1)
			{	
				$(this).prop("checked", true);
				viewCode += $(this).val() + ", ";
			}
			index ++;
		});
		viewCode = viewCode.substring(0, viewCode.length -2);
		$(dayId + num).html(viewCode);
		
		// 소요시간
		var timeCode = $(timeHidden + num).val().substring(0, 2); // 시간만 쪼개기
		timeCode = Number(timeCode); // 숫자로 변경해서 앞자리의 0을 없앤다.
		$("select[name=menu_time_cnt" + num + "]").val(timeCode); // edit에 반영.
		$(timeId + num).html(timeCode);
		
		
		
		
	} // for end
}

function createMenu(newCnt){
	
	var request = {};
	
	var menu_name = $("#menu_name_cnt" + newCnt).val();
	var menu_price = $("#menu_price_cnt" + newCnt).val();
	var menu_instruction = $("#menu_instruction_cnt" + newCnt).val();
	
	var menu_time = $("#id_menu_time_cnt"+newCnt).val(); // 숫자 1개
	if(Number(menu_time) < 10){ menu_time = "0" + menu_time.toString() + ":00"; // 현재에는 시간 단위이므로
	}else{ menu_time = menu_time.toString() + ":00"; }
	
	var menu_day = "";
	$("input[name=menu_day_cnt" + newCnt + "]").each(function(){  // edit에 반영.
		if($(this).is(":checked")){
			menu_day += "1";	}
		else{
			menu_day += "0";	}
		menu_day += "/";
	});
	// 마지막 구분자는 없애고 request.
	menu_day = menu_day.substring(0, menu_day.length -1);

	request["menu_name"] = menu_name;
	request["menu_price"] = menu_price;
	request["menu_instruction"] = menu_instruction;
	request["menu_time"] = menu_time;
	request["menu_day"] = menu_day;
	
	alert(JSON.stringify(request));

	$.ajax({
		url: "createMenu",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){
	    	
	    	// 등록된 값을 .list 에 담아준다.
	    	
	    	
	    	// 다른 카드와 같게 만들고, 다시 새로 등록하는 창을 띄운다.
	    	$("#card_area.contents>.card").removeClass("edit");
	    	$("#card_area.contents>.card").removeClass("new");
	    	$(".new_menu").show();
	    	
	    	alert("새로운 상품이 등록되었습니다.");
	    	
	    	
	    	
	    	
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end
} // function end



function modifyMenu(cnt){
	var request = {};
	// 기존의 정보와 변경된 것을 가려냄(if)
	if($("#menu_name_cnt"+cnt).val() != $("#view_menu_name_cnt"+cnt).text()){
		var menu_name = $("#menu_name_cnt"+cnt).val();
		request["menu_name"] = menu_name; }
	if($("#menu_price_cnt"+cnt).val() != $("#view_menu_price_cnt"+cnt).text()){
		var menu_price = $("#menu_price_cnt"+cnt).val();
		request["menu_price"] = menu_price; }
	if($("#menu_instruction_cnt"+cnt).val() != $("#view_menu_instruction_cnt"+cnt).text()){
		menu_instruction = $("#menu_instruction_cnt"+cnt).val();
		request["menu_instruction"] = menu_instruction; }

	var menu_time = $("#id_menu_time_cnt"+cnt).val(); // 숫자 1개
	if(Number(menu_time) < 10){ menu_time = "0" + menu_time.toString() + ":00"; // 현재에는 시간 단위이므로
	}else{ menu_time = menu_time.toString() + ":00"; }
	if(menu_time != $("#view_menu_time_hidden_cnt"+cnt).val())
	{ request["menu_time"] = menu_time; }

	var menu_day = "";
	$("input[name=menu_day_cnt" + cnt + "]").each(function(){  // edit에 반영.
		if($(this).is(":checked")){
			menu_day += "1"; }
		else{
			menu_day += "0"; }
		menu_day += "/";
	});
	// 마지막 구분자는 없애고 request.
	menu_day = menu_day.substring(0, menu_day.length -1);
	if(menu_day != $("#view_menu_day_hidden_cnt"+cnt).val())
	{ request["menu_day"] = menu_day; }

	// 변경사항이 없다.
	if(JSON.stringify(request).length <= 2)
	{	alert("변경 사항이 존재하지 않습니다!");
		return;	}
	
	request["menu_seq"] = $("#menu_seq_cnt"+cnt).val();
	
	alert(JSON.stringify(request));
	
	$.ajax({
		url: "modifyMenu",
		type :"POST",
	    contentType : "application/json;charset=UTF-8",
       	data: JSON.stringify(request),
	    success:function(response){

	    	alert("변경사항이 저장되었습니다.");
	    	
		}, // success end
	    error: function(request, status, error){
			alert("error! 관리자에게 문의하세요.");
			alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	    }
	}); // ajax end
} // function end
