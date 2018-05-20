<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
		
		if(!confirm("${msg}")){
			// 
			${func}
		}else{
			// 기존의 데이터 삭제 후 reservation 리로드.
			var $form = $("<form></form>");
			
			$form.attr("action", "deleteConfirm");
			$form.attr("method", "post");
			$form.appendTo("action", "body");
			
			var del_seq = $("<input type='hidden' name='schedule_seq' value='" + "${schedule_seq}" + "'>");
			
			var req_ymd = $("<input type='hidden' name='schedule_ymd' value='" + "${scheduleInfoModel.schedule_ymd}" + "'>");
        	var req_year = $("<input type='hidden' name='schedule_year' value='" + "${scheduleInfoModel.schedule_year}" + "'>");
        	var req_month = $("<input type='hidden' name='schedule_month' value='" + "${scheduleInfoModel.schedule_month}" + "'>");	
        	var req_date = $("<input type='hidden' name='schedule_date' value='" + "${scheduleInfoModel.schedule_date}" + "'>");
        	
        	var req_time = $("<input type='hidden' name='schedule_hm' value='" + "${scheduleInfoModel.schedule_hm}" + "'>");
        	var req_hour = $("<input type='hidden' name='schedule_hour' value='" + "${scheduleInfoModel.schedule_hour}" + "'>");
        	var req_min = $("<input type='hidden' name='schedule_min' value='" + "${scheduleInfoModel.schedule_min}" + "'>");
        	
        	var req_menu = $("<input type='hidden' name='menu_seq' value='" + "${scheduleInfoModel.menu_seq}" + "'>");
        	var req_member = $("<input type='hidden' name='member' value='" + "${scheduleInfoModel.member}" + "'>");
        	
        	
        	$form.append(del_seq).append(req_ymd).append(req_year).append(req_month).append(req_date).append(req_time).append(req_hour).append(req_min).append(req_menu).append(req_member);
        	$form.submit();
			
			
			$form.submit();
		}
		</script>
	</head>
	<body>
	</body>
</html>