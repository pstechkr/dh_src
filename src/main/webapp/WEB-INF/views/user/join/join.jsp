<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="nomenuTemplate">
<tiles:putAttribute name="title">회원가입 :: 이색 데이트 - 엘루비</tiles:putAttribute>
<tiles:putAttribute name="body">
<!--  jQuery UI -->
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/themes/smoothness/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/join.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/facebook.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user/animatescroll.min.js"></script>
<nav id="lnb">
	<h2>회원가입</h2>
</nav>
<section id="join">
	<div class="join_visual">
		<span>당신의 사랑을 키워주는 데이트 플래너! 엘루비에 오신 것을 환영합니다.</span>
	</div>
	<section class="agreement">
		<h3>이용약관 및 개인정보 처리 방침</h3>
		<div>
			<p>
엘루비 이용 약관<br><br>
엘루비 서비스 이용 약관은 관계 법령의 규정을 반영하여 아래와 같이 내용을 정하고 있습니다. <br>
이 이용약관은 웹사이트 및 모바일 어플리케이션 등 타인커뮤니케이션즈(이하 “회사”)에서 제공하는 엘루비 서비스(이하 “서비스”) 전체에 대하여 귀하와 회사 사이에 적용 됩니다. <br>
회사는 아래의 내용에 동의하는 회원에 한해 정상적인 서비스를 제공할 수 있습니다. 내용에 동의하지 않으면 회사에서 제공하는 모든 서비스에 대한 이용이 불가능합니다.<br>
<br>제1조(목적)<br>
이 약관은 회사가 제공하는 서비스 이용과 관련하여 회사와 회원의 가입, 이용 시 권리와 의무, 책임사항 등 기타 필요한 사항을 규정함을 목적으로 합니다.<br>
<br>제2조(용어의 정의)<br>
가. 회원 : 서비스를 제공받기 위하여 이용자 아이디(ID)를 소유하며 약관에 따라 권리와 의무를 갖는 자를 말합니다.<br>
나. 회원 계정 : 회원이 서비스를 이용하기 위하여 회원에게 주어지는 고유 식별 기호입니다.<br>
다. 파트너 : 회원 중에서 자신의 판매 콘텐츠나 회사와 함께 기획, 제작한 콘텐츠를 등록하고, 서비스에서 자신의 콘텐츠를 제공하는 회원으로, 회사의 선정 및 파트너 가입절차를 통해 파트너 자격을 획득할 수 있습니다.<br>
라. 상점 페이지 : 파트너에게 제공되는 파트너의 소개 및 콘텐츠 판매, 회원과 연락 가능한 게시판 등으로 이루어진 페이지를 의미합니다.<br>
마. 엘루비 프로젝트 : 회사가 파트너와 함께 기획, 제작 또는 파트너가 직접 기획, 제작하여 회원이 구매할 수 있도록 마련한 콘텐츠 중에서 엘루비 프로젝트 페이지에 매대를 둔 상품을 의미합니다.<br>
바. 상점 콘텐츠 : 회사가 파트너와 함께 기획, 제작 또는 파트너가 직접 기획, 제작하여 회원이 구매할 수 있도록 마련한 콘텐츠 중에서 상점 페이지 내부에 매대를 둔 콘텐츠를 의미합니다.<br>
<br>제3조(약관의 효력)<br>
가. 효력 발생 : 회원으로 가입함과 동시에 효력이 발생합니다. 관계 법령에 위배되지 않는 범위 안에서 개정이 될 수 있으며, 이는 공지사항 및 이메일로 통보하여 효력을 인정받습니다.<br>
나. 이 약관에 대한 동의는 엘루비 웹사이트나 모바일 어플리케이션을 방문하여 약관의 변경사항을 확인하는 것에 대한 동의를 포함합니다.<br>
다. 회원은 개정된 약관에 동의하지 않을 경우 회사에 서비스 탈퇴를 요청할 수 있으며, 계속 서비스를 이용하는 경우 변경된 약관에 동의하는 것으로 간주합니다.<br>
<br>제4조(약관 외 준칙)<br>
이 약관에 명시되지 않은 사항은 전기통신기본법, 전기통신사업법 및 기타 관련법령의 규정에 따릅니다.<br>
<br>제5조(회원가입)<br>
가. 회원이 되고자 하는 자는 서비스의 가입양식에 따라 회원정보를 작성하고 “약관에 동의”하여 회원 가입을 신청합니다.<br>
나. 회원이 입력한 정보는 사실로 간주됩니다. 회사는 회원이 입력한 정보의 내용이 사실과 다를 경우(차명, 비실명, 허위 정보 등)와 타인의 정보를 도용한 사실이 명백하게 확인 되는 경우, 회사는 해당 회원의 권한을 삭제하며 서비스의 전면적인 이용을 거부할 수 있고, 이로인해 발생하는 모든 불이익은 회사가 책임지지 않습니다. <br>
다. 회원의 등급에 따라 서비스 제공의 차별을 둘 수 있으며, 회원은 그 등급에 따라 서비스 이용에 제약을 받을 수 있습니다.<br>
라. 서비스는 가 항목과 같은 방법으로 회원 가입을 신청한 회원이 아래의 각 호에 해당하는 조건을 충족하지 못할 경우 회원 가입 승낙을 유보 또는 거부할 수 있습니다.<br>
(ㄱ)등록내용 허위게재 사실이 있는 경우.<br>
(ㄴ)가입 신청 회원이 엘루비 약관 및 서비스 이용에 관한 관계법령을 위반하여 회원 자격이 상실된 자.<br>
(ㄷ) 사회적 질서 및 미풍양속에 문란이 되는 행위자.<br>
(ㄹ)기타 회사의 여건 상 이용 승낙이 곤란하거나 가입 결격 사유에 해당된 자.<br>
마. 회원가입 계약 성립 시점은 회사의 가입 승낙이 가입 신청자에게 도달한 시점을 기준으로 합니다.<br>
<br>제6조(서비스의 종류 및 범위)<br>
가. 회사에서는 무료로 서비스 일반 회원이 될 수 있습니다. 단, 일부 서비스 이용 시 유료 회원으로 전환 가능합니다.<br>
나. 가입 후 승인이 완료된 회원은 서비스에서 제공하는 모든 콘텐츠를 열람할 수 있으며, 엘루비 프로젝트 및 상점 콘텐츠 신청 등의 서비스를 이용할 수 있습니다.<br>
다. 유료 서비스의 사용<br>
(ㄱ) 콘텐츠 신청 서비스를 이용하기 위해서는 콘텐츠 건별로 각각 유료 구매를 하여야 합니다.<br>
(ㄴ) 유료 서비스 구매에 있어서 회원 가입과는 별도로 추가적인 결제과정(휴대전화, 계좌이체, 신용카드 등)을 거치며 회원의 동의 하에 결제하도록 합니다.<br>
(ㄷ) 유료 서비스의 종류 및 결제현황(구매내역)은 홈페이지에서 확인할 수 있으며, 콘텐츠의 종류 및 가격은 회사의 운영 방침에 따라 추가, 수정, 보완, 중지될 수 있습니다.<br>
라. 회사에서는 양질의 서비스 제공을 위해 파트너들의 콘텐츠 수정, 보완, 중지 등을 요청할 수 있으며, 회사의 내부 교정에 따라 일부 서비스 이용을 제한할 수 있습니다.<br>
마. 서비스를 통해 “모임”이 성사된 이후 회원들 간 행위의 책임은 회원들에게 귀속됩니다. 회사에서는 신고된 회원의 행위가 추후 다른 회원에게 추가적인 피해를 입힐 수 있다고 판단되는 경우, 문제 회원을 탈퇴 혹은 아이디 영구 삭제 등 후속 조치를 취할 수 있습니다.<br>
<br>제7조(서비스 내 콘텐츠 저작권)<br>
가. 게시물에 대한 권리와 책임은 작성자에게 있으며, 회사는 게시자의 동의 없이 게시물을 영리 목적으로 사용할 수 없습니다.<br>
나. 엘루비 프로젝트 및 상점 콘텐츠의 권리와 책임은 해당 콘텐츠의 기획 및 제작의 참여도를 포함한 회사와 파트너 사이의 합의에 따릅니다.<br>
다. 회원은 서비스를 이용하여 얻은 정보를 임의 가공, 판매하는 행위, 기타 방법에 의하여 서비스에 개제된 자료를 상업적으로 사용하거나 제 3자에게 이용할 수 없습니다.<br>
라. 회사는 파트너의 콘텐츠 혹은 회사가 권리를 행사할 수 있는 저작물을 토대로 영리적인 목적의 2차 저작물(영상, 책자 등)을 제작할 수 있으며, 이 경우 파트너의 저작물에 대하여는 각 파트너의 동의 하에 진행됩니다.<br>
마. 회사는 회원이 서비스 내에 게시하거나 등록하는 내용물 및 게시 내용이 회사의 목적과 맞지 않는 경우 사전통지 없이 삭제하거나 이동 또는 등록 거부할 수 있습니다.<br>
<br>제8조(환불 및 결제 취소)<br>
가. 회원은 회사가 제공하는 다양한 결제수단을 통해 유료 서비스를 이용할 수 있으며, 결제가 비정상적으로 처리되어 정상처리를 요청할 경우 회사는 회원의 결제 금액을 정상처리 할 의무가 있습니다.<br>
나. 회사는 부정한 방법 또는 회사가 금지한 방법을 통해 결제한 금액에 대하여 이를 취소하거나 환불을 제한할 수 있습니다.<br>
다. 회원은 다음 각 호의 사유가 있으면 아래의 라 항의 규정에 따라 회사로부터 결제 취소, 환불 및 보상을 받을 수 있습니다.<br>
(ㄱ) 결제를 통해 사용할 수 있는 서비스가 전무하며 그에 대한 책임이 전적으로 회사에 있을 경우 (단, 시스템 정기정검 등의 불가피한 경우를 제외)<br>
(ㄴ) 회사 또는 결제대행사의 시스템 오류로 인하여 결제 기록이 중복으로 발생한 경우.<br>
(ㄷ) 서비스 중단 등의 회사 잘못으로 인해 회사가 회원에게 해지를 통보하는 경우.<br>
(ㄹ) 기타 소비자 보호를 위하여 당사에서 따로 정하는 경우.<br>
라. 환불, 결제 취소 절차는 다음 각 호와 같습니다.<br>
(ㄱ) 환불을 원하는 회원은 회원 본인임을 입증하는 절차를 고객센터를 통해 접수하여야 하며 이와 동시에 환불을 신청하여야 합니다.<br>
(ㄴ) 회사는 환불 신청이 정담함을 심사한 후 정당한 이유가 있음으로 판명된 회원에게 환불 합니다.<br>
(ㄷ) 회사는 회원에게 환불되어야 할 금액을 회원에게 통보하고 난 후 회원이 결제한 방법으로 해당 환불 및 결제 취소 처리합니다.<br>
마. 회원이 이용 약관 및 서비스 이용 정책에 위반한 행위로 인한 사용정지 또는 강제 탈퇴되는 경우 환불 및 보상하지 않습니다.<br>
바. 회사는 회원이 유료 서비스를 이용하는 중이거나 이용한 후 해당 이용료를 환불해 드리지 않습니다.<br>
사. 회원이 자진탈퇴로 인해 계약이 해지되는 경우, 회원이 보유한 아이템은 자동으로 소멸되어 복구 및 환불이 불가능 합니다.<br>
아. 회사와 이용자 간에 발생한 분쟁은 전자거래기본법 제28조 및 동 시행령 제15조에 의하여 설치된 전자거래분쟁조정위원회의 조정에 따를 수 있습니다.<br>
자. 제휴 및 협력사를 통해 구매한 상품권이나 혹은 이벤트 상품 및 기타 상품은 기본적으로 환불 및 보상이 불가능하며, 별도 공지된 환불규정이 있을 경우에는 그 규정을 따릅니다.<br>
차. 회사는 다 항에 해당하는 경우에도 아래 각 호에 해당하는 경우 환불을 하지 않습니다.<br>
(ㄱ) 다른 이용자로부터 선물 받은 아이템의 경우.<br>
(ㄴ) 이벤트 당첨 또는 참여로 적립 받은 아이템의 경우.<br>
(ㄷ) 서비스 내에서 활동 등으로 회사로부터 적립받은 경우.<br>
(ㄹ) 기타 이용자가 직접 결제하지 않은 경우.<br>
<br>제9조(회사의 의무)<br>
가. 회사는 지속적이고 안정적인 서비스 제공을 위해 최선을 다합니다.<br>
나. 회사에서는 회사에서 정한 약관 및 운영 정책을 위반하는 불량 회원들을 강제 탈퇴 혹은 관계 법령에 따라 법적 절차를 진행할 의무를 가집니다.<br>
다. 회사는 정보통신망이용촉진등에 관한 법률 등 관계법령에 따라 회원이 회원 가입 신청 시 기록한 개인정보, 이후에 추가로 기록한 개인정보 및 서비스 이용 중 생성되는 개인정보를 보호하여야 합니다.<br>
라. 회사의 임직원은 서비스 제공과 관련, 회원의 개인정보를 회원 외에는 제 3자에게 누설 또는 배포해서는 안 되며, 이를 어길 시 직위 해제의 사유가 될 수 있습니다.<br>
마. 회사는 정기적으로 회원에게 서비스 내용 및 이용 안내, 중요한 운영 정책의 변경, 약관의 변경사항 등 서비스 운영에 관련한 제반사항에 대해 정기적인 서신을 보냅니다. (공지, 문자, 이메일 등)<br>
바. 회사는 이용 계약의 체결, 계약 사항의 변경 및 해지 등 회원과의 계약 관련 절차 및 내용 등에 있어 이용 고객에게 편의를 제공하도록 최선을 다합니다.<br>
<br>제10조(회원의 의무)<br>
가. 회원 가입 시 작성되는 프로필은 사실이어야 합니다. 또한 일정 기간이 경과한 뒤 변경되는 개인 정보는 지속적으로 본인이 정보를 갱신하여야 합니다.<br>
나. 이를 성실히 이행하지 않은 상황에서의 불이익은 개인의 책임 입니다.<br>
다. 회원이 직접 입력한 개인정보는 진실하여야 하며 만일 위 정보가 허위로 작성한 사실이 적발되어 다른 회원에게 정신적, 물질적 손해가 발생할 경우 위 문제의 책임은 회원 본인에게 귀속됩니다.<br>
라. 회원의 거짓된 정보에 관련하여 타 회원의 피해가 발생할 경우, 민형사상의 책임은 개인 정보를 허위로 게재한 회원에게 귀속 됩니다.<br>
마. 서비스 내에 게재된 각 회원들의 자료는 각 등록 회원 및 회사의 저작물로 서비스 외 제 3의 공간에 무단으로 게재할 수 없으며 상업적 용도로 이용할 수 없습니다. 해당 행위가 적발되면 탈퇴 조치 및 관계 법령에 의거 의법조치 될 수 있습니다.<br>
바. 회원은 아래 각 호에 해당하는 행위를 해서는 안 됩니다.<br>
(ㄱ) 서비스의 회원 정보를 부정하게 취득하는 행위.<br>
(ㄴ) 서비스를 해킹 또는 유사 프로그램을 이용하여 정상적인 운영을 어렵게 하는 행위. (예: 해킹 또는 바이러스, DDOS 공격 등)<br>
(ㄷ) 상대방의 ID와 비밀번호를 도용하는 행위.<br>
(ㄹ) 개인 정보를 허위 또는 타인의 것으로 등록하는 행위.<br>
(ㅁ) 상대방에 대한 비방의 글 또는 인격 모독에 해당하는 글을 올린 행위.<br>
(ㅂ) 공공질서, 미풍양속을 저해하는 저작물을 등록 또는 유통시킨 경우.<br>
(ㅅ) 서비스 내 불법적으로 물건을 판매하거나 상행위를 하는 경우.<br>
(ㅇ) 음란물 및 기타 동영상을 유포하는 행위.<br>
(ㅈ) 프로필 및 개인정보, 사이트 내 콘텐츠를 외부로 유출하는 행위.<br>
(ㅊ) 기타 회사의 운영 정책을 위반하는 행위.<br>
사. 위 바 항 각 호에 해당 사항에 명시한 내용을 위반한 회원은 강제 탈퇴 될 수 있으며 민형사상의 책임을 지게 됩니다.<br> 
아. 본 약관을 위반하거나 기타 대한민국 관계 법령에 위반하는 행위에 대해서는 경고 없이 회원의 권한이 박탈되며, 이로 인한 어떠한 보상도 회사는 회원에게 부여하지 않습니다.<br>
<br>제11조(계약의 종료)<br>
가. 회사는 다음의 각 호와 같은 조건을 계약의 종료 조건으로 인정합니다.<br>
(ㄱ) 회원의 자의에 의한 탈퇴 신청.<br>
(ㄴ) 회원의 의무를 성실하게 이행하지 않거나 약관에서 정한 회원의 의무 및 이용 정책에 위반하는 행위를 한 회원은 사전 동의 없이 강제 탈퇴 처리될 수 있습니다.<br>
(ㄷ) 기타 회원의 여건 상 지속적인 계약을 이행하지 못한다고 판단될 경우 임의 탈퇴 처리할 수 있습니다. (예: 사망 또는 행방불명, 3개월 이상 활동을 하지 않는 휴면 계정 회원 등)<br>
<br>제12조(면책 조항)<br>
가. 회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 대한 책임이 면제됩니다.<br>
나. 회사는 기간통신 사업자가 전기통신 서비스를 중지하거나 정상적으로 운영을 하지 못해 발생하는 경우 책임이 면제 됩니다.<br>
다. 회사는 회원의 귀책사유로 인한 서비스 이용 장애에 대한 책임이 면제됩니다.<br>
라. 회사는 회원이 회사의 서비스 제공으로부터 기대되는 이익을 얻지 못했거나 서비스 자료에 대한 취소, 선택 또는 이용으로 인해 발생하는 손해 등에 대해 책임이 면제됩니다.<br>
마. 회사는 회원이 제공한 회원의 개인 정보의 신뢰도 및 정확도에 대한 책임이 면제됩니다.<br>
바. 회사의 게시물에 회원이 게재한 글의 저작권은 회원 본인에게 있으며, 이 정보의 진실성 및 저작권에 위배되어 민 형사 상의 문제가 발생 시 책임은 회원 본인에게 있습니다.<br>
사. 회사는 회원의 서비스 이용에 필요한 서버의 보수작업으로 인한 교체, 일시 정지, 리뉴얼 작업이 발생할 경우 책임이 면제 됩니다.<br>
아. 회원의 개인 정보 부주의로 인하여 내부적인 공지사항이 전달되지 못하는 경우 그 책임은 회원 본인에게 있습니다.<br>
자. 회사는 회원들의 신원을 보증하지 않습니다. 또한 회원이 다른 회원에게 경제적, 정신적, 물질적 피해를 가해도 그 책임은 회원 본인에게 있습니다.<br>
차. 서비스 내에 게재된 제3의 사이트 주소에 관하여 회사는 어떠한 통제권도 없으며 이로 인하여 발생되는 손해는 회원 본인에게 있습니다.<br>
카. 회사는 회원이 본 약관 규정을 위반하여 발생한 손실에 대해서는 책임을 지지 않습니다.<br>
타. 회원이 공개를 허락한 자료에 대하여 발생되는 모든 문제에 대해서는 회사의 책임이 면제됩니다.<br>
파. 회사의 동의 없이 제 3자에게 ID 또는 회원의 권한을 판매 및 양도할 경우, 회원 자격 발탈 및 민 형사상의 고발을 취할 수 있으며, 이로 인한 모든 피해는 불법으로 회원 권한을 판매 및 양도한 회원에게 귀속됩니다.<br>
<br><br>부칙<br>
이 약관은 2014년 7월 21일부터 유효합니다.</p>
			<input type="checkbox" id="chk_1"><label for="">엘루비 이용약관에 동의합니다.</label>
		</div>
		<div>
			<p>
엘루비(www.elever.kr) 등 타인커뮤니케이션즈가 제공하는 서비스의 개인정보 처리방침<br><br>
타인커뮤니케이션즈(이하 “회사”)는 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.<br>
회사의 개인정보 처리방침은 다음과 같은 내용을 담고 있습니다.<br><br>
1. 회사가 수집하고 있는 이용자의 개인 정보<br>
2. 회사의 개인정보 수집 및 이용 목적<br>
3. 회사에서 개인정보를 수집하는 방법<br>
4. 개인정보의 보유 및 이용 기간<br>
5. 정보주체의 권리와 의무 및 행사방법<br>
6. 개인정보의 제3자 제공에 관한 사항<br>
7. 개인정보 파기절차 및 방법<br>
8. 개인정보의 안전성 확보 조치<br>
9. 개인정보관리 책임자 및 담당자의 연락처<br> 
10. 개인정보 처리방침의 변경<br><br>
<br>1. 회사가 수집하고 있는 이용자의 개인 정보<br>
<br>가. 수집하는 개인정보의 항목<br>
- 이메일 주소, 비밀번호, 생년월일, 연락처 등의 정보는 회사의 개인정보 취급 방침을 동의하는 경우 수집됩니다.<br> 
- 서비스 이용을 위해 회원이 작성 혹은 회사의 인터뷰 등을 통해 얻게 된 콘텐츠, 사진 등의 정보는 회사의 개인정보 취급 방침을 동의하는 경우 수집 및 게재 됩니다.<br>
- 회사에서는 또한 아래의 항목들에 대해서 안정적인 서비스 제공을 위해 합법적인 절차와 회원의 동의를 거쳐 추가로 수집할 수 있습니다.<br>
- IP Address, 쿠키, 방문일시, 서비스 이용 기록, 불량 이용 기록<br>
- 사용 이동통신사, 계좌번호 등<br>
- 신용카드 결제시 : 카드사명, 카드번호 등<br> 
- 휴대전화 결제시 : 이동전화번호, 통신사, 결제승인번호 등<br>
- 계좌이체로 결제시 : 은행명, 계좌번호 등<br>
- 상품권 이용시 : 상품권 번호 등<br>
<br>나. 개인정보 수집방법<br>
회사는 다음과 같은 방법으로 개인 정보를 수집하고 있습니다.<br>
- 홈페이지, 서면양식, 팩스, 전화, 이메일, 이벤트 응모<br>
- 협력 회사로부터 공동 제휴 및 협력을 통한 정보 수집<br>
- 생성 정보 수집 툴을 통한 정보 수집<br>
<br>2. 회사의 개인정보 수집 및 이용 목적<br>
<br>가. 서비스 제공에 관한 계약 이행<br>
- 회원 가입 의사확인, 회원제 서비스 제공에 따른 본인 식별 및 인증, 회원 자격 유지 및 관리, 각종 고지 및 통지, 고충 처리 등을 목적으로 개인정보를 처리합니다.<br>
<br>나. 재화 또는 서비스 제공에 따른 요금 정산<br>
- 서비스 제공, 콘텐츠 제공, 맞춤 서비스 제공, 본인 인증, 구매 및 요금 결제, 요금 추심 등을 목적으로 개인정보를 처리합니다.<br>
<br>다. 마케팅 및 광고에의 활용<br>
- 신규 서비스(제품) 개발, 이벤트 및 광고성 정보의 제공과 참여기회 제공, 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계 등을 목적으로 개인정보를 처리합니다.<br>
<br>3. 회사에서 개인정보를 수집하는 방법<br>
모든 회원들이 서비스를 제공받기 위해서는 개인정보가 필요하며, 정보수집은 회원가입 시 회원가입 양식에 사용자의 동의를 통해 개인정보를 수집합니다.<br>
<br>4. 개인정보의 보유 및 이용 기간<br>
회원의 개인정보는 회원 가입 후 서비스 이용 기간이 종료되거나 회원이 계약 해지, 탈퇴 등의 사유로 이메일이나 서면을 통해 개인 정보 삭제를 요구하는 경우에는 제3자의 열람과 이용이 불가능한 상태로 처리되며, 전자 상거래 등에서의 소비자 보호에 관한 법률 제6조, 정보통신 이용촉진 및 정보보호 등에 관한 법률에 의하여 아래의 명시 기간 동안 보관관리 합니다.<br>
가. 계약, 청약철회, 회원서비스 제공 등의 거래에 관한 기록 : 5년<br>
나. 대금결제 및 재화 등의 공급에 관한 기록 : 5년<br>
다. 소비자 불만 또는 분쟁 처리에 관한 기록 : 3년<br>
라. 본인 확인에 관한 기록 : 6개월<br>
마. 방문에 관한 기록 : 3개월<br>
바. 회사 내부 방침에 의한 정보보유 사유<br>
- 부정이용 기록<br>
- 보존 이유 : 부정 이용 방지<br>
- 보존 기간 : 3년<br>
<br>5. 정보주체의 권리와 의무 및 행사방법<br>
가.정보주체는 회사에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.<br> 
(ㄱ) 개인정보 열람 요구<br>
(ㄴ) 오류 등이 있을 경우 정정 요구<br>
(ㄷ) 삭제 요구<br>
(ㄹ) 처리 정지 요구<br>
나. 가 항에 따른 권리 행사는 회사에 대해 개인정보보호법 시행규칙 별지 제8호 서식에 따라 서면, 전자우면 등을 통하여 하실 수 있으며 회사는 이에 대해 지체 없이 조치하겠습니다.<br>
다. 정보 주체가 개인정보의 오류 등에 대한 정정 또는 삭제를 요구한 경우에는 회사는 정정 또는 삭제를 완료할 때까지 당해 개인정보를 이용하거나 제공하지 않습니다.<br>
라. 가 항에 따른 권리 행사는 정보 주체의 법정 대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하여야 합니다.<br>
<br>6. 개인정보의 제3자 제공에 관한 사항<br>
회사는 정보 주체의 동의, 법률의 특별한 규정 등 개인정보 보호법 제17조 및 제18조에 해당하는 경우에만 개인 정보를 제3자에게 제공합니다.<br>
<br>7. 개인정보의 파기절차 및 방법<br>
회사는 원칙적으로 개인정보 처리 목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.<br>
가. 파기절차<br>
이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져 (종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.<br>
나. 파기기한<br>
이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.<br>
다. 파기방법<br>
- 전자적 파일 형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다.<br>
- 종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.<br>
<br>8. 개인정보의 안전성 확보 조치<br>
회사는 개인정보보호법 제29조에 따라 다음과 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 취하고 있습니다.<br>
가. 개인정보 취급 직원의 최소화 및 교육<br>
개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화하여 개인정보를 관리하는 대책을 시행하고 있습니다.<br>
나. 개인정보의 암호화<br>
이용자의 비밀번호는 암호화되어 저장 및 관리되고 있어 본인만이 알 수 있으며, 중요한 데이터는 파일 및 전송 데이터를 암호화 하는 보안 기능을 사용합니다.<br>
다. 서비스의 일시 중단<br>
회사는 이용자의 안전한 서비스 이용을 위해 최선을 다하고 있습니다. 그러나 원하지 않는 방법에 의해 서비스가 훼손을 당하는 경우 이용자들의 개인정보 보호를 위하여 문제가 완전하게 해결될 때까지 이용자의 개인정보를 이용한 서비스를 일시 중단할 수 있습니다.<br>
<br>9. 개인정보 보호 책임자 작성<br>
회사는 개인정보 처리에 관한 업무룰 총괄해서 책임지고 개인정보처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.<br>
개인정보 보호 책임자<br>
이름 : 장지한<br>
직위 : 관리책임자<br>
전화 : 010-2536-8654<br>
이메일 : admiral@tiein.co.kr<br><br>
이름 : 오성호<br>
직위 : 관리담당자<br>
전화 : 010-3213-4404<br>
이메일 : ggergger@tiein.co.kr<br>
<br>10. 개인정보 처리방침 변경<br>
개인정보처리방침은 시행일로부터 적용되며, 법령 및 방침에 따른 변경 내용의 추가, 삭제 및 정정이 있는 경우에는 변경사항의 시행 7일 전부터 공지사항을 통하여 고지할 것입니다.<br>
<br>부칙<br>
이 약관은 2014년 7월 21일부터 적용됩니다.
			</p>
			<input type="checkbox" id="chk_2"><label for="">개인정보 처리방침에 동의합니다.</label>
		</div>
	</section>
	<div class="header_button">
		<div class="sect_header">
			<h2>가입 방법 선택</h2>
			<span>이메일 주소 또는 facebook 계정으로 가입할 수 있습니다.</span>
		</div>
		<div class="btn login_button">
			<button class="general" onclick="javascript:emailClick();">이메일 주소로 회원 가입</button><button class="facebook" onclick="javascript:joinfacebooklogin();">facebook으로 회원 가입</button>
		</div>
		<div class="clear"></div>
	</div>
	<div class="hidden">
	<form name="userRegisterInfo" id="userRegisterInfo" method="post" onsubmit="return false;">
		<section class="common_info">
			<div class="sect_header">
				<h2>기본 정보 입력</h2>
				<span>가입 시 필수적으로 입력해야하는 정보입니다.</span>
			</div>
			<dl class="user_id">
				<dt>이메일</dt>
				<dd><input type="text" id="user_email" name="user_email"><button class="btn_email_check" onclick="javascript:email_check();">중복확인</button>
					<input type="hidden" id="user_email_check" value=""></dd>
			</dl>
			<dl class="user_pw">
				<dt>비밀번호</dt>
				<dd><input type="password" id="user_password" name="user_password" maxlength="14"><span>6~14자리의 숫자와 영문 조합</span></dd>
			</dl>
			<dl class="user_pw2">
				<dt>비밀번호 확인</dt>
				<dd><input type="password" id="user_repassword" name="user_repassword" maxlength="14"></dd>
			</dl>
		</section>
		<section class="add_info">
			<div class="sect_header">
				<h2>구매 정보 입력</h2>
				<span>상품 구매 시 필요한 정보입니다.</span>
			</div>
			<dl class="user_name">
				<dt>성명</dt>
				<dd><input type="text" id="user_name" name="user_name" maxlength="10"></dd>
			</dl>
			<dl class="user_gender">
				<dt>성별</dt>
				<dd>
					<input type="radio" name="gender" value="0"><label>남자</label>
					<input type="radio" name="gender" value="1"><label>여자</label>
					<input type="hidden" id="user_gender" name="user_gender">
				</dd>
			</dl>
			<dl class="user_birthday">
				<dt>생년월일</dt>
				<dd>
					<input type="text" id="user_birthday_year" name="user_birthday_year" readonly="readonly">년
					<input type="text" id="user_birthday_month" name="user_birthday_month" readonly="readonly">월
					<input type="text" id="user_birthday_day" name="user_birthday_day" readonly="readonly">일
					<input type="hidden" id="user_birthday" name="user_birthday">
					<input type=hidden id="datepicker"/>
				</dd>
			</dl>
			<dl class="user_phone">
				<dt>핸드폰</dt>
				<dd>
					<input type="text" id="user_phoneno1" name="user_phoneno1" maxlength="3"> - <input type="text" id="user_phoneno2" name="user_phoneno2" maxlength="4"> - <input type="text" id="user_phoneno3" name="user_phoneno3" maxlength="4">
					<input type="hidden" id="user_phoneno" name="user_phoneno">
				</dd>
			</dl>
		</section>
	</form>
	<button class="join_submit general" onclick="javascript:register();">이메일 주소로 회원 가입</button>
	</div>
	<!-- <button class="join_submit general" onclick="javascript:register();">이메일 주소로 회원 가입</button> -->
	<!-- <button class="join_submit facebook">facebook으로 회원 가입</button> -->
</section>
</tiles:putAttribute>
</tiles:insertDefinition>