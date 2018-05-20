
// 페이지 번호
function pagination(num){
	
	url = location.href;

	// 현재 주소에서 ? 없다면,
	if(url.indexOf("?") == -1){
		location.href = url + "?p=" + num;
	}else{
		// ?가 있는 경우
		// 페이지 parameter 없는 경우 
		if(url.indexOf("p=") == -1){
			location.href = url + "&p=" + num;
		}else{
			// 페이지 parameter 있는 경우
			var split = url.split("p=");
			location.href = split[0] + "p=" + num;
		}
	}
}

// 페이지 좌우 버튼
function btnPagination(key){
	
	var this_page = $(".pagination .this_page").text();
	
	if(key == "prev"){
		var prev_page = Number(this_page) - 1;
		
		if(prev_page <= 0){
			alert("첫 페이지 입니다.");
		}else{
			pagination(prev_page);	
		}
		
	}else if(key == "next"){
		var next_page = Number(this_page);
		var last_page = Number($("#last_page").val());
		
		if(next_page >= last_page){
			alert("마지막 페이지 입니다.");
		}else{
			pagination(next_page+1);	
		}
		
	}
}
