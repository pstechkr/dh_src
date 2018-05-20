$(document).ready(function() {
	$(".item").click(function(){
		if(!$(this).hasClass("active")){
			if(!$(".item_detail").is(":animated")){
				$(this).addClass("active");
				
				var seq = $("#store_seq").val();
				calendarRequest("ready", seq);
				$(this).children(".item_detail").slideDown();
			}
		}
	});
	$(".close_btn>button").click(function(){
		$(this).parents(".item_detail").slideUp();
		$(this).parents(".item").removeClass("active");	
	});
});