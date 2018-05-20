<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="nomenuTemplate">
<tiles:putAttribute name="title">회사 소개 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<nav id="lnb">
	<h2>회사 소개</h2>
</nav>
<section id="introduce">
	<section class="contents">
    		<h2>무슨 회사인가요?</h2>
            <div class="company box">
                <div class="image">
                    <img src="${pageContext.request.contextPath}/resources/img/main/logo_tiein.png" alt="Tiein Communications">
                </div>
                <div class="message">
                    <p>
                        타인커뮤니케이션즈.<br> 무수한 ‘타인’들을 연결하고, 하나로 ‘묶어주자’는 의미를 담았습니다.</p>
                        <p>우리는 '엘루비'를 통해 세상에서 가장 가까운 타인인 커플들의 사이가 더욱 끈끈해질 수 있는 다양한 방법들을 고민합니다.</p>
                        <p>팍팍한 일상 속 단비 같은 그 사람과의 오늘 하루를 더욱 즐겁고 신나게 보내는 방법을 발견하고, 우리네의 연애가 한발짝 더 성장할 수 있기를 바라는 사람들이 모인 곳이 바로 타인커뮤니케이션즈 입니다.</p>
                </div>
            	<div style="clear:both">
            	</div>
			</div>
		
        	<%-- <h2>무슨 사업을 하고 있나요?</h2>
    		<div class="tieiner box">

                <div class="image">
                <img src="${pageContext.request.contextPath}/resources/img/main/shinyoungbok.png" alt="어리석은 사람의 우직함이 세상을 조금씩 바꿔나갑니다.">
                <!-- <span>ⓒ신영복 (http://www.shinyoungbok.pe.kr)</span> -->
                </div>
                <div class="message">
                        <p>엘루비는 프랑스말로 '자라나다, 들어올리다'라는 뜻을 가지고 있습니다.</p>
                        <p>'사랑으로 자라다.' </p>
                        <p>작성중</p>
                </div>
            	<div style="clear:both">
            	</div>
			</div> --%>
			
        	<h2>당신들은 누구인가요?</h2>
    		<div class="tieiner box">

                <div class="image">
                <img src="${pageContext.request.contextPath}/resources/img/main/shinyoungbok.png" alt="어리석은 사람의 우직함이 세상을 조금씩 바꿔나갑니다.">
                <span>ⓒ신영복 (http://www.shinyoungbok.pe.kr)</span>
                </div>
                <div class="message">
						<p>자기를 세상에 잘 맞추는 현명한 사람이기보단 어리석게도 세상을 자기에게 맞추려 하는 사람들입니다.</p>
						<p>우리가 세상을 변화시킬 것이란 믿음 하나 손에 쥔 채로 주변의 걱정과 우려에 뒤돌아보지 않으며 지금까지 버텨왔고, 확신할 순 없지만 오늘도 미래의 언젠가를 생각하며 우직하게 또 한 발을 내딛는 사람들입니다.</p>
						<p>우리들의 우직함이 진정 세상을 변화시킬 수 있을까요?</p>
                </div>
            	<div style="clear:both">
            	</div>
			</div>
			
        	<h2>연락 주세요.</h2>
            <div class="contact box">
                <div class="image">
                <span>elever@tiein.co.kr</span>
				</div>
				<div class="message">
					<p>많은 커플들이 더 즐겁고 재미난 데이트 프로그램을 만날 수 있도록<br>'엘루비'와 함께할 수 있는 좋은 의견을 보내 주세요.</p>
                </div>
            	<div style="clear:both">
            	</div>
			</div>
	</section>
</section>
</tiles:putAttribute>
</tiles:insertDefinition>
