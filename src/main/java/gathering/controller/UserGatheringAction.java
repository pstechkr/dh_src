package gathering.controller;

import gathering.model.GatheringScheduleInfoModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import tiein.method.TieinDefault;
import user.model.UserInfoModel;

@Controller
@RequestMapping("/gathering")
public class UserGatheringAction {
	
	// 예약신청 :: 로그인 세션 체크
	@RequestMapping(value = "/loginSessionCheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> loginSessionCheck(HttpSession httpsession){

		TieinDefault tieinDefault = new TieinDefault();
		String sessionCheck = tieinDefault.loginSessionCheck(httpsession);
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("sessionCheck", sessionCheck);
		return rtnMap;
	}
	
	// 예약신청 :: 예약확정
	@RequestMapping("/{seq}/confirmCommit")
	public ModelAndView confirmCommit(@PathVariable("seq") String gathering_seq, HttpSession httpsession, HttpServletRequest request, @ModelAttribute GatheringScheduleInfoModel gatheringScheduleInfoModel){
		
		boolean isCheck = false;
		String userGatheringMapper = "gathering.mapper.UserGatheringMapper.";	// db setting
		String couponMapper = "service.mapper.CouponMapper.";
		String user_seq = (String)httpsession.getAttribute("user_seq");
		
		gatheringScheduleInfoModel.setGathering_seq(gathering_seq);
		gatheringScheduleInfoModel.setUser_seq(user_seq);

		gatheringScheduleInfoModel.setStatus("1");

		// 주문번호 만들기. 20140710-Gxxxxxxxxxx
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(now);

		TieinDefault tieinDefault = new TieinDefault();
		String rand = tieinDefault.randomNum(10);
		
		// 주문번호 생성, model에 넣기
		String order_no = date + "-G" + rand;
		gatheringScheduleInfoModel.setOrder_no(order_no);
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			session.insert(userGatheringMapper + "insert_gathering_schedule", gatheringScheduleInfoModel);
			
			// 쿠폰 사용시.
			if(!gatheringScheduleInfoModel.getCoupon_seq().equals("")){
				
				Map<String, String> couponUse = new HashMap<String, String>();
				couponUse.put("coupon_id", gatheringScheduleInfoModel.getCoupon_seq());
				couponUse.put("schedule_seq", gatheringScheduleInfoModel.getSchedule_seq());
				couponUse.put("product", "project");
				
				session.update(couponMapper + "update_couponUse", couponUse);
				System.out.println("update");
			}
			
			session.commit();
			isCheck = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		if(isCheck){
			String redirect = request.getSession().getServletContext().getContextPath() + "/mypage/ticket";
			ModelAndView mav = new ModelAndView("alert");
			mav.addObject("msg","예약 신청이 완료되었습니다!");
			mav.addObject("func","location.href='" + redirect + "';");
			return mav;
		}
		return new ModelAndView(new RedirectView(request.getSession().getServletContext().getContextPath() + "/gathering/" + gathering_seq ));
	}
	
	// 예약신청 :: 팝업 로그인
	@RequestMapping(value = "/popuplogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> popuplogin(@RequestBody HashMap<String, String> map, HttpSession httpsession){
		
		// model setting
		UserInfoModel userInfoModel = new UserInfoModel();
		userInfoModel.setUser_email(map.get("user_email"));
		userInfoModel.setUser_password(map.get("user_password"));
		
		TieinDefault tieinDefault = new TieinDefault();
		boolean isSuccess = tieinDefault.loginCheck(userInfoModel, httpsession);
		
		// JSON 정렬
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		if(!isSuccess){
			rtnMap.put("isSuccess", "N");
			rtnMap.put("msg", "아이디 혹은 비밀번호가 일치하지 않습니다.");
		}else{
			rtnMap.put("isSuccess", "Y");
			rtnMap.put("location", "gathering");
			
		}
		return rtnMap;
	}
	
	// 예약신청 :: 페이스북 팝업 로그인
	@RequestMapping(value = "/popupfacebooklogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> popupfacebooklogin(@RequestBody UserInfoModel userInfoModel, HttpSession httpsession){
		
		TieinDefault tieinDefault = new TieinDefault();
		boolean isSuccess = tieinDefault.facebookLogin(userInfoModel, httpsession);
		
		// JSON 정렬
		Map<String, Object> rtnMap = new HashMap<String, Object>();

		if(!isSuccess){
			rtnMap.put("isSuccess", "N");
			rtnMap.put("msg", "페이스북 로그인에 실패 했습니다.");
		}else{
			rtnMap.put("isSuccess", "Y");
			rtnMap.put("location", "gathering");
			
		}
		return rtnMap;
	}
}
