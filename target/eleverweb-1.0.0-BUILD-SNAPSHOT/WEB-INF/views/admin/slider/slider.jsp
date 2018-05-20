<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="adminTemplate">
<tiles:putAttribute name="title">관리자 인덱스 슬라이더 관리 페이지</tiles:putAttribute>
<tiles:putAttribute name="body">
<style>
.contents div{width:1000px;font-size:16px;margin:0 auto;}
.contents textarea{width:900px;min-height:100px}
</style>
<script type="text/javascript">

function sliderRequest(){
	
	var image = $("#slider_image").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	image = image.replace(/(\s*)/g,"");
	$("#slider_image").val(image);
	
	var link = $("#slider_link").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	link = link.replace(/(\s*)/g,"");
	$("#slider_link").val(link);
	
	var comment = $("#slider_comment").val().replace(/\n/g,"<br>"); // 엔터 <br>로 변경
	comment = comment.replace(/(\s*)/g,"");
	$("#slider_comment").val(comment);
	
	var form = document.getElementById("sliderForm");
	form.action = "sliderRequest";
	form.submit();
	
}
</script>
<section class="contents">
	<form name="sliderForm" id="sliderForm" method="post" onsubmit="return false;">
		<div>
			파일 등록(900px * 330px)<br><textarea id="slider_image" name="slider_image">${sliderInfoModel.slider_image}</textarea>
		</div>
		<div>
			링크 주소<br><textarea id="slider_link" name="slider_link">${sliderInfoModel.slider_link}</textarea>
		</div>
		<div>
			코멘트<br><textarea id="slider_comment" name="slider_comment">${sliderInfoModel.slider_comment}</textarea>
		</div>
		<div>
			<button onclick="javascript:sliderRequest();">저장</button>
		</div>
	</form>
</section>
	
</tiles:putAttribute>
</tiles:insertDefinition>
			
