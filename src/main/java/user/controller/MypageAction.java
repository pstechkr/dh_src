package user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.model.CouponInfoModel;
import tiein.method.TieinDefault;
import user.model.AccountInfoModel;
import user.model.UserInfoModel;

@Controller
@RequestMapping("/mypage")
public class MypageAction {
	
	
	// 예약취소
	@RequestMapping(value = "/rsvCancelRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> rsvCancelRequest(@RequestBody Map<String, String> INPUT, HttpSession httpsession){	
		
		String user_seq = (String)httpsession.getAttribute("user_seq");
		INPUT.put("user_seq", user_seq);
		String product = INPUT.get("product");
		// 테이블 이름
		if(product.equals("project")){
			INPUT.put("table", "gathering_schedule_info");
		}else if(product.equals("store")){
			INPUT.put("table", "schedule_info");
		}
		TieinDefault tieinDefault = new TieinDefault();
		boolean isCheck = false;
		String mypageMapper = "user.mapper.MypageMapper.";	// db setting 
		String couponMapper = "admin.mapper.CouponMapper.";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// 결제 내역 정보를 불러옴
			AccountInfoModel accountInfoModel = session.selectOne(mypageMapper + "select_reservation_account_one", INPUT);
			
			// 쿠폰 발급 내역이 있으면, 
			if(accountInfoModel != null){
				// 쿠폰을 다시 발급
				CouponInfoModel coupon = tieinDefault.couponCreate("", 1, "", "", "");
				INPUT.put("coupon_id",coupon.getCoupon_id());
				INPUT.put("coupon_seq", accountInfoModel.getCoupon_seq());
				
				System.out.println("coupon recreate : " + accountInfoModel.getCoupon_seq());
				session.insert(couponMapper + "insert_rsv_cancel_recreate", INPUT);
				
			}
			// 해당 건은 예약 취소
			session.update(mypageMapper + "update_reservation_cancel", INPUT);
			
			isCheck = true;
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		Map<String, Object> OUTPUT = new HashMap<String, Object>();
		OUTPUT.put("isCheck", isCheck);
		return OUTPUT;
	}

	// 회원정보 수정 페이지 이전에 비밀번호 체크하는 CheckModify에서의 Request
	@RequestMapping("/modify")
	public ModelAndView passwordCheckRequest(@ModelAttribute UserInfoModel userInfoModel, HttpServletRequest request, HttpSession httpsession){
	
		ModelAndView mav = new ModelAndView();
		
		// 시컨스 받기
		userInfoModel.setUser_seq((String)httpsession.getAttribute("user_seq"));
		
		// 비밀번호 암호화
		TieinDefault tieinDefault = new TieinDefault();
		userInfoModel.setUser_password(tieinDefault.passSHA(userInfoModel.getUser_password()));
	
		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			userInfoModel = session.selectOne(userMapper + "select_checkModify", userInfoModel);
			
			if(userInfoModel.isSuccess()){
				
				System.out.println("checkModify");
				MypageController mypageController = new MypageController();
				return mypageController.modify(request, httpsession);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		String redirect = request.getSession().getServletContext().getContextPath() + "/mypage/checkModify";
		mav.setViewName("alert");
		mav.addObject("msg","비밀번호를 다시 확인해 주세요!");
		mav.addObject("func","location.href='" + redirect + "';");
		
		return mav;
	}
	
	// 회원정보 수정
	@RequestMapping("/modifyRequest")
	public ModelAndView modifyRequest(@ModelAttribute UserInfoModel userInfoModel, HttpServletRequest request, HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();
		boolean isCheck = false;
		
		// 시컨스 받기
		userInfoModel.setUser_seq((String)httpsession.getAttribute("user_seq"));
		
		// 비밀번호 암호화
		if(!userInfoModel.getUser_password().equals("")){
			TieinDefault tieinDefault = new TieinDefault();
			userInfoModel.setUser_password(tieinDefault.passSHA(userInfoModel.getUser_password()));
		}
		// 이름을 바꾼 경우 session에서도 변경
		httpsession.setAttribute("user_name", userInfoModel.getUser_name());
		
		
		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			session.update(userMapper + "update_userInfo", userInfoModel);
			session.commit();
			isCheck = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		if(isCheck){
			
			String redirect = request.getSession().getServletContext().getContextPath() + "/mypage/checkModify";
			mav.setViewName("alert");
			mav.addObject("msg","정보가 수정 되었습니다!");
			mav.addObject("func","location.href='" + redirect + "';");
		}
		
		return mav;
	}
	
	
	// 즐긴 상품 평가하기
	@RequestMapping(value = "/pointRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> pointRequest(@RequestBody Map<String, String> INPUT, HttpSession httpsession){
		
		// 시컨스 받기
		String user_seq = (String)httpsession.getAttribute("user_seq");
		INPUT.put("user_seq", user_seq);
		String product = INPUT.get("product");
		// 테이블 이름
		if(product.equals("project")){
			INPUT.put("table", "gathering_schedule_info");
		}else if(product.equals("store")){
			INPUT.put("table", "schedule_info");
		}
		
		String mypageMapper = "user.mapper.MypageMapper.";	// db setting 
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		boolean isCheck = false;
		
		try
		{
			session.update(mypageMapper + "update_product_point", INPUT);
			isCheck = true;
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		Map<String, Object> OUTPUT = new HashMap<String, Object>();
		OUTPUT.put("isCheck", isCheck);
		return OUTPUT;
		
	}
}
