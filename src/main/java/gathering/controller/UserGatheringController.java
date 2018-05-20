package gathering.controller;

import gathering.model.GatheringInfoModel;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.model.CouponInfoModel;
import service.model.TagInfoModel;
import tiein.method.TieinDefault;
import user.model.UserInfoModel;


// gathering main
// gathering list

@Controller
@RequestMapping("/gathering")
public class UserGatheringController {
	
	@RequestMapping("/index")
	public ModelAndView gathering_index(){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/gathering/gathering_index");
		return mav;
		
	}
	
	@RequestMapping("/{seq}")
	public ModelAndView gathering_main(@PathVariable("seq") String gathering_seq, HttpServletRequest request, HttpServletResponse response){
		
		
		TieinDefault tieinDefault = new TieinDefault();
		boolean count = tieinDefault.cookieCheck("gathering", gathering_seq, request, response);
		
		ModelAndView mav = new ModelAndView();
		boolean isApply = true;
		String userGatheringMapper = "gathering.mapper.UserGatheringMapper.";	// db setting
		GatheringInfoModel gatheringInfoModel = new GatheringInfoModel();
		List<TagInfoModel> tag_list = new ArrayList<TagInfoModel>();
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			// gathering 정보 불러오기 
			gatheringInfoModel = session.selectOne(userGatheringMapper + "select_gatheringInfo", gathering_seq);
//			System.out.println(gatheringInfoModel);
//			System.out.println("태그 : " + gatheringInfoModel.getGathering_tag());
//			System.out.println("멤버 : " + gatheringInfoModel.getGathering_member());
			
			if(count){
				session.update(userGatheringMapper + "update_gathering_count", gatheringInfoModel.getGathering_seq());
				session.commit();
			}
			
			// tag 체크
//			if(gatheringInfoModel.getGathering_tag() != null && !gatheringInfoModel.getGathering_tag().equals("")){
//				String[] gathering_tag = null;
//				gathering_tag = gatheringInfoModel.getGathering_tag().split("/");
//				tag_list = session.selectList(userGatheringMapper + "select_gathering_main_tag", gathering_tag);
//			}
			
		
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		
		// member null인 경우 0으로.
		System.out.println(gatheringInfoModel.getGathering_member());
		if(gatheringInfoModel.getGathering_member() == null){
			gatheringInfoModel.setGathering_member("0");
		}
		
		// max 와 member 간의 차이 구하여 isApply T/F, isApply인 경우 최대 선택가능인원 체크.
		int member = Integer.parseInt(gatheringInfoModel.getGathering_member());
		int max = Integer.parseInt(gatheringInfoModel.getGathering_max());
		int apply = 0;
		if(member >= max){
			isApply = false;
		}else{
			apply = max - member;
			if(apply>10){
				// 최대 10명까지 신청할 수 있도록 함.
				apply = 10;
			}
		}
		// 숫자 콤마
		gatheringInfoModel.setGathering_price(String.format("%,d",Integer.parseInt(gatheringInfoModel.getGathering_price())));
		
		// 슬라이더
		boolean isSlider = false;
		// slider split
		if(gatheringInfoModel.getGathering_slide_image() != null){
			if(!gatheringInfoModel.getGathering_slide_image().equals("")){
				String[] sliderList = gatheringInfoModel.getGathering_slide_image().split("<br>");
				mav.addObject("sliderList", sliderList);
				isSlider = true;
			}
		}
		mav.setViewName("/user/gathering/gathering_main");
		mav.addObject("gatheringInfoModel", gatheringInfoModel);
		mav.addObject("isSlider", isSlider);
		mav.addObject("tag_list", tag_list);
		mav.addObject("isApply", isApply);
		mav.addObject("apply", apply);
		
		return mav;
	}
	
	// 예약신청 :: confirm 화면 로드
	@RequestMapping("/{seq}/confirm")
	public ModelAndView gathering_reservation(@PathVariable("seq") String gathering_seq, HttpServletRequest request, HttpSession httpsession){
		
		ModelAndView mav = new ModelAndView();
		String userMapper = "user.mapper.UserMapper.";	// db setting
		String userGatheringMapper = "gathering.mapper.UserGatheringMapper.";	// db setting
		String couponMapper = "service.mapper.CouponMapper.";	// db setting
		String user_seq = (String)httpsession.getAttribute("user_seq");
		
		UserInfoModel userInfoModel = new UserInfoModel();
		userInfoModel.setUser_seq(user_seq);
		
		List<CouponInfoModel> couponList = new ArrayList<CouponInfoModel>();
		
		
		GatheringInfoModel gatheringInfoModel = new GatheringInfoModel();

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			userInfoModel = session.selectOne(userMapper + "select_userInfo", userInfoModel);
			couponList = session.selectList(couponMapper + "select_couponList_one", user_seq);
			gatheringInfoModel = session.selectOne(userGatheringMapper + "select_gatheringInfo", gathering_seq);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		String price = gatheringInfoModel.getGathering_price();
		String member = request.getParameter("member");
		int _gathering_price = Integer.parseInt(price) * Integer.parseInt(member);
		
		// 쿠폰 가격
		for(int i=0; i < couponList.size(); i++){
		
			// 정가
			if(couponList.get(i).getCoupon_div().equals("0")){
					int tmp_price = _gathering_price - Integer.parseInt(couponList.get(i).getCoupon_price());
					String _price = String.format("%,d",tmp_price);
					couponList.get(i).setTotal_price(_price);
			}
			// 퍼센트가
			else{
					int percent = 100 - Integer.parseInt(couponList.get(i).getCoupon_price());
					int tmp_price = (_gathering_price * percent) / 100;
					String _price = String.format("%,d",tmp_price);
					couponList.get(i).setTotal_price(_price);
			}
		}
		// 정상가 
		String gathering_price = String.format("%,d",_gathering_price);
		
		mav.setViewName("/user/gathering/gathering_confirm");
		mav.addObject("userInfoModel", userInfoModel);
		mav.addObject("gatheringInfoModel", gatheringInfoModel);
		mav.addObject("member", member);
		mav.addObject("gathering_price", gathering_price);
		mav.addObject("couponList", couponList);
		
		
		return mav;
		
	}
	
}
