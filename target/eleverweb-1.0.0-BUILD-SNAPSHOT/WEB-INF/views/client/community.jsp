<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="clientTemplate">
<tiles:putAttribute name="title">고객 관리</tiles:putAttribute>
<tiles:putAttribute name="body">

<div id="contents">
	<section id="customer_qa">
		<section class="contents">
			<h2>Q / A</h2>
			<section class="comment_wrap">
				<div class="comment_box">
					<div class="comment_header">
						<span>닉네임</span>
						<span>0000. 00. 00</span>
					</div>
					<div class="comment">
						<p>좋은 경험 하고 갑니다! 다른 커플들에게도 추천해주고 싶어요~^^ 친절하게 잘 알려주셔서 감사합니다!<br>
						다음에 또 오겠습니다~</p>
					</div>
					<div class="comment_footer">
						<textarea name="" id="" cols="30" rows="10"></textarea>
						<button>등록</button>
					</div>
				</div>
				<div class="comment_box">
					<div class="comment_header">
						<span>닉네임</span>
						<span>0000. 00. 00</span>
					</div>
					<div class="comment">
						<p>좋은 경험 하고 갑니다! 다른 커플들에게도 추천해주고 싶어요~^^ 친절하게 잘 알려주셔서 감사합니다!<br>
						다음에 또 오겠습니다~</p>
					</div>
					<div class="comment_footer">
						<textarea name="" id="" cols="30" rows="10"></textarea>
						<button>등록</button>
					</div>
				</div>
				<div class="comment_box">
					<div class="comment_header">
						<span>닉네임</span>
						<span>0000. 00. 00</span>
					</div>
					<div class="comment">
						<p>좋은 경험 하고 갑니다! 다른 커플들에게도 추천해주고 싶어요~^^ 친절하게 잘 알려주셔서 감사합니다!<br>
						다음에 또 오겠습니다~</p>
					</div>
					<div class="comment_footer">
						<textarea name="" id="" cols="30" rows="10"></textarea>
						<button>등록</button>
					</div>
				</div>
			</section>
			<section class="comment_load">
				<button>댓글 더보기</button>
			</section>
		</section>
	</section>
</div>

</tiles:putAttribute>
</tiles:insertDefinition>

