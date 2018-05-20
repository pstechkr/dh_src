// cookie에 아이디 저장

// 체크박스 클릭

function setCookie(name, value, expiredays){
	
	var today = new Date();
	today.setDate(today.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + today.toGMTString() + ";";
}

function getCookie(name){
	
	var isCheck = false;
	var start = 0;
	var end = 0;
	var i = 0;
	
	while(i <= document.cookie.length){
		start = i;
		end = start + name.length;
		if(document.cookie.substring(start, end) == name){
			isCheck = true;
			break;
		}
		i++;
	}
	
	if(isCheck == true){
		start = end + 1;
		end = document.cookie.indexOf(";", start);
		if(end < start){ end = document.cookie.length; }
		return document.cookie.substring(start, end);
	}
	
	return "";
	
}
