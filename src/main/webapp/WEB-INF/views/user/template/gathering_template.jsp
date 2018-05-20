<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
    <head>
    	<tiles:insertAttribute name="general_head" />
    	<tiles:insertAttribute name="head" />
   		<title><tiles:insertAttribute name="title" ignore="true" /></title>
   		<script type="text/javascript">
			$(document).ready(function(){
				$(".item_nav>ul>li>a").eq(0).addClass("active");
			});
		</script>
	</head>
	<body>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<nav id="lnb">
			<h2 class="gathering">${gatheringInfoModel.gathering_name}</h2>
		</nav>
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>