package user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.model.CouponInfoModel;
import tiein.method.TieinDefault;
import user.model.TicketInfoModel;
import user.model.UserInfoModel;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	// 구매내역 페이지
	@RequestMapping("/purchase")
	public ModelAndView purchase_list(HttpServletRequest request, HttpSession httpsession){
	
		ModelAndView mav = new ModelAndView();
		String mypageMapper = "user.mapper.MypageMapper."; // mapper
		String user_seq = (String)request.getSession().getAttribute("user_seq"); // user_seq
		String total_count = "0";
		// 페이징 처리 // 페이지 번호 매기기
		TieinDefault tieinDefault = new TieinDefault();
		
		String row = "5"; // row
		int page;if(request.getParameter("p") == null){page = 1;}else{page = Integer.parseInt(request.getParameter("p").toString());} // 현재 page
		String sqlPage = Integer.toString(((page-1) * Integer.parseInt(row))); // sql에 들어가는 page
		int pagination_row = 10; // 하단에 보여질 페이지 번호 매기기의 갯수

		// 전체 페이지 수 (pageList)
		Map<String, Object> selectPageMap = new HashMap<String, Object>();
		selectPageMap.put("user_seq", user_seq);
		selectPageMap.put("row", row);
		selectPageMap.put("pagination_row", pagination_row);
		
		// 예약 내역 로드
		Map<String, String> selectTicketMap = new HashMap<String, String>();
		selectTicketMap.put("user_seq", user_seq);
		selectTicketMap.put("page", sqlPage);
		selectTicketMap.put("row", row);
		// 월
		String month = request.getParameter("m");if(month == null){month = "1";} // month
		selectTicketMap.put("month", month);
		selectPageMap.put("month", month);
		// MODEL
		List<TicketInfoModel> ticketInfoList = new ArrayList<TicketInfoModel>();
		

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			// 예약 내역 기간별 로드
			ticketInfoList = session.selectList(mypageMapper + "select_all_purchase", selectTicketMap);
			total_count = session.selectOne(mypageMapper + "select_all_purchase_count", selectPageMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		
		
		// 페이지 번호 매기기
		// 전체 페이지 수, 현재 페이지, 하단에 보여질 페이지 번호 매기기의 갯수를 이용.
		// 형변환.
		Map<String, Object> output = tieinDefault.pagination(total_count, row, pagination_row, page);
		
		mav.setViewName("/user/mypage/purchase_list");
		mav.addObject("ticketInfoList", ticketInfoList);
		mav.addObject("pagination", output.get("pagination"));
		mav.addObject("last_page", output.get("total_pagination"));
		mav.addObject("this_page", page);
		mav.addObject("total_count", total_count);
		
		return mav;
	}
	
	
	
	
	// 예약티켓
	@RequestMapping("/ticket")
	public ModelAndView ticket(HttpServletRequest request, HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();
		
		String mypageMapper = "user.mapper.MypageMapper."; // mapper
		String user_seq = (String)request.getSession().getAttribute("user_seq"); // user_seq
		String total_count = "0";
		// 페이징 처리 // 페이지 번호 매기기
		TieinDefault tieinDefault = new TieinDefault();
		
		String row = "5"; // row
		int page;if(request.getParameter("p") == null){page = 1;}else{page = Integer.parseInt(request.getParameter("p").toString());} // 현재 page
		String sqlPage = Integer.toString(((page-1) * Integer.parseInt(row))); // sql에 들어가는 page
		int pagination_row = 10; // 하단에 보여질 페이지 번호 매기기의 갯수

		// 전체 페이지 수 (pageList)
		Map<String, Object> selectPageMap = new HashMap<String, Object>();
		selectPageMap.put("user_seq", user_seq);
		selectPageMap.put("row", row);
		selectPageMap.put("pagination_row", pagination_row);
		
		
		// 예약 내역 로드
		Map<String, String> selectTicketMap = new HashMap<String, String>();
		selectTicketMap.put("user_seq", user_seq);
		selectTicketMap.put("page", sqlPage);
		selectTicketMap.put("row", row);
		
		// MODEL
		List<TicketInfoModel> ticketInfoList = new ArrayList<TicketInfoModel>();
		

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			// 예약 내역 로드.
			if(request.getParameter("product") == null){
				
				ticketInfoList = session.selectList(mypageMapper + "select_all_ticket", selectTicketMap);
				total_count = session.selectOne(mypageMapper + "select_all_ticket_count", selectPageMap);
				
			}else if(request.getParameter("product").equals("gathering")){
				
				ticketInfoList = session.selectList(mypageMapper + "select_gathering_ticket", selectTicketMap);
				selectPageMap.put("table", "gathering_schedule_info");
				total_count = session.selectOne(mypageMapper + "select_ticket_count", selectPageMap);
				
			}else if(request.getParameter("product").equals("store")){

				ticketInfoList = session.selectList(mypageMapper + "select_store_ticket", selectTicketMap);
				selectPageMap.put("table", "schedule_info");
				total_count = session.selectOne(mypageMapper + "select_ticket_count", selectPageMap);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 페이지 번호 매기기
		// 전체 페이지 수, 현재 페이지, 하단에 보여질 페이지 번호 매기기의 갯수를 이용.
		// 형변환.
		
		Map<String, Object> output = tieinDefault.pagination(total_count, row, pagination_row, page);
		
		mav.setViewName("/user/mypage/ticket_list");
		mav.addObject("ticketInfoList", ticketInfoList);
		mav.addObject("pagination", output.get("pagination"));
		mav.addObject("last_page", output.get("total_pagination"));
		mav.addObject("this_page", page);
		mav.addObject("total_count", total_count);
		return mav;
		
	}
	
	
	// 정보수정 이전 체크
	@RequestMapping("/checkModify")
	public ModelAndView checkId(HttpServletRequest request, HttpSession httpsession){
	
		String userMapper = "user.mapper.UserMapper.";
		String user_seq = (String)httpsession.getAttribute("user_seq");
		String division = "";
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			division = session.selectOne(userMapper + "select_user_division", user_seq);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		if(division.equals("facebook")){
			return modify(request, httpsession);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/mypage/check_password");
		return mav;
		
	}
	
	// 정보수정 페이지 (/modify Action에서 넘어옴)
//	@RequestMapping("/modify")
	public ModelAndView modify(HttpServletRequest request, HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();

		// sql setting
		String userMapper = "user.mapper.UserMapper.";
		UserInfoModel userInfoModel = new UserInfoModel();
		userInfoModel.setUser_seq((String)httpsession.getAttribute("user_seq"));
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			userInfoModel = session.selectOne(userMapper + "select_userInfo", userInfoModel);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 생일, 연락처 구분하여 내보내기. 성별 글자로 바꾸어 보내기.
		
		if(userInfoModel.getUser_birthday() != null){
			String[] birthday = userInfoModel.getUser_birthday().split("-");
			mav.addObject("birthday", birthday);
		}
		
		if(userInfoModel.getUser_phoneno() != null){
			String[] phoneno = userInfoModel.getUser_phoneno().split("-");
			mav.addObject("phoneno", phoneno);
		}
		
		String gender = "";
		if(userInfoModel.getUser_gender().equals("0")){
			gender = "남성";
		}else{
			gender = "여성";	
		}
		
		mav.setViewName("/user/mypage/modify");
		mav.addObject("model", userInfoModel);
		
		mav.addObject("gender", gender);
		
		return mav;
	}
	
	// 쿠폰관리
	@RequestMapping("/coupon")
	public ModelAndView coupon(HttpServletRequest request, HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();
		String mypageMapper = "user.mapper.MypageMapper."; // mapper
		String user_seq = (String)request.getSession().getAttribute("user_seq"); // user_seq
		String query = (String)request.getParameter("q"); 
		
		System.out.println("query : " + query);
		
		Map<String, Object> count = new HashMap<String, Object>();
		// 페이징 처리 // 페이지 번호 매기기
		TieinDefault tieinDefault = new TieinDefault();
		
		String row = "5"; // row
		int page;if(request.getParameter("p") == null){page = 1;}else{page = Integer.parseInt(request.getParameter("p").toString());} // 현재 page
		String sqlPage = Integer.toString(((page-1) * Integer.parseInt(row))); // sql에 들어가는 page
		int pagination_row = 10; // 하단에 보여질 페이지 번호 매기기의 갯수

		// 전체 페이지 수 (pageList)
		Map<String, Object> selectPageMap = new HashMap<String, Object>();
		selectPageMap.put("user_seq", user_seq);
		selectPageMap.put("row", row);
		selectPageMap.put("pagination_row", pagination_row);
		
		
		// 예약 내역 로드
		Map<String, String> selectCouponMap = new HashMap<String, String>();
		selectCouponMap.put("user_seq", user_seq);
		selectCouponMap.put("page", sqlPage);
		selectCouponMap.put("row", row);
		// 사용가능 버튼 클릭시
		selectCouponMap.put("query", "");
		if(query != null){
			if(query.equals("unused")){
				selectCouponMap.put("query", "0");
				System.out.println("unused");
			}
		}
		
		// MODEL
		List<CouponInfoModel> couponInfoList = new ArrayList<CouponInfoModel>();
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			// total_count.
			count = session.selectOne(mypageMapper + "select_coupon_total_count", selectCouponMap);
			couponInfoList = session.selectList(mypageMapper + "select_coupon_list", selectCouponMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		System.out.println(count);
		String total_count = String.valueOf(count.get("total_count"));
		String unused_count = String.valueOf(count.get("unused_count"));
		// 페이지 번호 매기기
		// 전체 페이지 수, 현재 페이지, 하단에 보여질 페이지 번호 매기기의 갯수를 이용.
		// 형변환.
		
		Map<String, Object> output = tieinDefault.pagination(total_count, row, pagination_row, page);
		
		mav.setViewName("/user/mypage/coupon_list");
		mav.addObject("pagination", output.get("pagination"));
		mav.addObject("last_page", output.get("total_pagination"));
		mav.addObject("this_page", page);
		mav.addObject("unused_count", unused_count);
		
		mav.addObject("couponInfoList", couponInfoList);
		return mav;
	}
	
}
