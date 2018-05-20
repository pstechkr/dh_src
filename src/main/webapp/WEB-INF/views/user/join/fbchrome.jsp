<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
		<!-- <script id="facebook-jssdk" src="//connect.facebook.net/ko_KR/all.js#xfbml=1&amp;appId=271344896402165&version=v2.0"></script> -->
		<script id="facebook-jssdk" src="//connect.facebook.net/ko_KR/all.js#xfbml=1&amp;appId=201629896706999&version=v2.0"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	</head>
	<body>
		<script type="text/javascript">
		$(document).ready(function(){
			
			var item = $("#item").val();
			if(item != ""){
				var tmp = item.split(",");
				$("#seq").val(tmp[0]);
				$("#tmember").val(tmp[1]);
			}
			
		});
		setTimeout(function(){
			
			if($("#error").val() != ""){
				alert("Facebook 정보를 불러올 수 없습니다.");
				location.href="http://www.elever.kr/login";
			}
			
			
			FB.getLoginStatus(function(response){
				try{
			    	FB.api('/me', function(user){
			    		var seq = $("#seq").val();    		
			    		if(seq == ""){
							facebookRegister(user);
		   				}else{
		   					
		   					facebookRegisterSEQ(user,seq);
		   				}
		    		});
				}
		    	catch(err){
		    		// 취소 누른 경우
		    		alert("Facebook 정보를 불러올 수 없습니다.");
		    		location.href="http://www.elever.kr/login";
		    	}
			});
		});
		
		function facebookRegister(user){
			var user_email = $("<input type='hidden' name='user_email' value='" + user.email + "'>");
			var user_name = $("<input type='hidden' name='user_name' value='" + user.name + "'>");
			var user_birthday = $("<input type='hidden' name='user_birthday' value='" + user.birthday + "'>");
			var user_gender = $("<input type='hidden' name='user_gender' value='" + user.gender + "'>");
			var facebook_id = $("<input type='hidden' name='facebook_id' value='" + user.id + "'>");
			
			if(user.id == null){
				alert("Facebook 정보를 불러올 수 없습니다.");
				location.href="http://www.elever.kr/login";
				return false;
			}
			if(user.email == null){
				alert("Facebook 정보를 불러올 수 없습니다.");
				location.href="http://www.elever.kr/login";
				return false;
			}
			
			// form 생성
			var $form = $("<form></form>");
			$form.attr("action", "facebookLogin");
			$form.attr("method", "post");
			$form.appendTo("action", "body");
			
			$form.append(user_email).append(user_name).append(user_birthday).append(user_gender).append(facebook_id);
			$form.submit();
		}
		
		function facebookRegisterSEQ(user, seq){
			
			request = {};
			request["user_email"] = user.email;
			request["user_name"] = user.name;
			request["user_birthday"] = user.birthday;
			request["user_gender"] = user.gender;
			request["facebook_id"] = user.id;
			$.ajax({
				url: "gathering/popupfacebooklogin",
		        type :"POST",
		        contentType : "application/json;charset=UTF-8",
		       	data:  JSON.stringify(request),
		        success:function(response){
		        	
		        	if(response["isSuccess"] == "Y"){
		        		// 로그인 성공
		        		// 예약 로직 태우기.
		        		if(response["location"] == "gathering"){
		        			// confirm
		        			confirmMethod(seq);
		        		}else{
		//        			reservationRequest();
		        		}
		        	}else{
		        		// 로그인 실패
		        		alert(response["msg"]);
		        	}
		        }, // success end
		        error: function(){
					alert("error! 관리자에게 문의하세요.");
		        }
			}); // ajax end
		}
		
		function confirmMethod(seq){
			
			var mb = $("#tmember").val();
			// confirm
			
			var $form = $("<form></form>");
			$form.attr("action", "gathering/" + seq + "/confirm");
			$form.attr("method", "post");
			$form.appendTo("body");
			var member = $("<input type='hidden' name='member' value='" + mb + "'>");
			
			$form.append(member);
			$form.submit();
			
		}
		</script>
		<input type="hidden" id="error_reason" value="${param.error_reason}">
		<input type="hidden" id="error" value="${param.error}">
		<input type="hidden" id="error_description" value="${param.error_description}">
		<input type="hidden" id="item" value="${param.item}">
		<input type="hidden" id="seq" value="">
		<input type="hidden" id="tmember" value="">
	</body>
</html>