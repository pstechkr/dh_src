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


// filter
$(document).ready(function(){
	
	// 지역 전체보기 버튼
	$(".filter_header").on({
	
		click: function(event){
			if(!$(this).parents().children(".filter_menu").hasClass("active")){
				$(this).parents().children(".filter_menu").slideDown();
				$(this).parents().children(".filter_menu").addClass("active");
			}else{
				$(this).parents().children(".filter_menu").slideUp();
				$(this).parents().children(".filter_menu").removeClass("active");
			}
		}
	});
});