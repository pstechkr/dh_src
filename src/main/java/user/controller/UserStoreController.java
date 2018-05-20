package user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import user.model.UserInfoModel;
import client.model.MenuInfoModel;
import client.model.ScheduleInfoModel;
import client.model.StoreInfoModel;

@Controller
@RequestMapping("/store/{seq}")
public class UserStoreController {
	
	@RequestMapping("/intro")
	public ModelAndView store(@PathVariable("seq") String seq, HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		
		// 상점 불러오기
		// sql setting
		String storeMapper = "client.mapper.StoreMapper.";
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setStore_seq(seq);
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		// 정기휴무일 글자로 변경
		if(storeInfoModel.getStore_dayoff() != null){
			String[] dayoff = storeInfoModel.getStore_dayoff().split("/");
			String tmp = "";
			for(int i=0; i < dayoff.length; i++){
				
				if(dayoff[i].equals("1")){
					switch(i){
						case 1 : tmp += "일요일"; break;
						case 2 : tmp += "월요일"; break;
						case 3 : tmp += "화요일"; break;
						case 4 : tmp += "수요일"; break;
						case 5 : tmp += "목요일"; break;
						case 6 : tmp += "금요일"; break;
						case 7 : tmp += "토요일"; break;
						case 8 : tmp += "공휴일"; break;
					}
					tmp += ", ";
				}
			}
			tmp = tmp.substring(0, tmp.length()-2);
			storeInfoModel.setStore_dayoff(tmp);
		}
		
		
		mav.setViewName("/user/store_main");
		mav.addObject("model", storeInfoModel);
		return mav;
	}
	
	
	/*
	 * 
	 * 상품 예약 확인 페이지에 들어왔을 때, 우선 정보를 database에 담아둔다. (/confirmRequest)
	 * database에 담아둠으로써 다른 사람이 해당 시간에 예약을 할 수 없게되도록 하기 위함.
	 * 하지만 컨펌이 나지 않은 상태이므로 누군가가 상품예약 페이지를 로드했을 때, 5분이 지났으나 컨펌이 되지 않은 것들은 삭제해주도록 함. (/reservation 로드시에 이벤트 하나 만들 것)
	 * reservation 페이지 열 때 delete.
	 * confirmRequest 타서(예약하기를 눌러서) database에 넣기 전에 같은 시간에 같은 상품을 중복 예매하는것인지 체크가 필요함.
	 * (5분 전에 예매를 다시 하는 경우가 이에 해당되고 이럴 경우 두번 자리를 연속해서 잡아버리는 문제가 발생.) (/reservation에서 함께 체크할 것)
	 * 
	 * 
	 */
	
	
	// 상품 예약 페이지
	@RequestMapping("/reservation")
	public ModelAndView reservation(@PathVariable("seq") String seq, HttpServletRequest request){
	
		// 상품을 불러오는 일과 
		// 기존에 등록되어 있었으나 5분이 지나도 결제처리가 되지 않은 건에 대한 삭제도 함께 할 것.
		
		
		// 이미 예약하려고 했는데 confirm 안한 것이 있다면, 체크
		// store_seq, user_seq 예약을 진행하던 것 confirm_yn 이 0인 것이 있으면, 묻도록.
		// "예약 진행중인 상품이 있습니다. 새로운 예약을 진행할까요?"
		// 이 경우에는 로그인이 되어있어야만 가능. 
		
		ModelAndView mav = new ModelAndView();
		
		// 상품 불러오기
		// sql setting
		String menuMapper = "client.mapper.MenuMapper.";
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		List<MenuInfoModel> menuList = new ArrayList<MenuInfoModel>();
 		menuInfoModel.setStore_seq(seq);
	
 		// 상점 헤더 정보
 		String storeMapper = "client.mapper.StoreMapper.";
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		
		// schedule Mapper
		String scheduleMapper = "client.mapper.ScheduleMapper.";
 	

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{	
			// (1) 5분 지나도 결제처리 안 된 건에 대한 삭제.
			session.delete(scheduleMapper + "delete_temporary_schedule");
			
			
			
			// (2) 5분 이내에 있는 temporary_schedule Check (로그인 되어있어야 함)
			if(request.getSession().getAttribute("user_seq") != null){
				
				Map<String, String> temporaryCheck = new HashMap<String, String>();
				temporaryCheck.put("store_seq", seq);
				String user_seq = (String)request.getSession().getAttribute("user_seq");
				temporaryCheck.put("user_seq", user_seq);
				
				Map<String, String> temporaryModel = new HashMap<String, String>();
				temporaryModel = session.selectOne(scheduleMapper + "select_temporary_schedule", temporaryCheck);
				
				// 5분 이내 confirm 하지 않은 temporary_schedule이 존재한다면,
				if(temporaryModel.get("isCheck").equals("Y")){

					// javascript에서 Y/N 묻고 삭제하는 request 혹은 redirect. 
					ModelAndView temporaryMav = new ModelAndView("alert_confirm");
					temporaryMav.addObject("msg", "현재 예약 진행중인 상품이 있습니다.\\n확인을 누르면 기존의 예약을 취소하고 새로운 예약을 진행합니다.");
					temporaryMav.addObject("func", "location.href='" + request.getSession().getServletContext().getContextPath() + "/" + seq + "/confirm';");
					temporaryMav.addObject("divide", "01");
					temporaryMav.addObject("schedule_seq",temporaryModel.get("schedule_seq"));
					return temporaryMav;
				}
			}
			// 예약진행 상품 있는지 여부 체크.
			storeInfoModel = session.selectOne(storeMapper + "select_storeInfoHeader", seq); 
			menuList = session.selectList(menuMapper + "select_menuInfo", menuInfoModel);

			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		
		mav.setViewName("/user/store_reservation");
		mav.addObject("menuList", menuList);
		mav.addObject("model", storeInfoModel);
		return mav;
		
	}
	
	
	// 상품 예약 페이지 (reservation 에서 들어오는 것과 confirmRequest 에서 들어오는 것 두 가지가 로직을 처리한 후 마지막으로 이 로직을 타서 화면에 배치함.
	@RequestMapping("/confirm")
	public ModelAndView confirm(@PathVariable("seq") String seq, HttpServletRequest request){

		ScheduleInfoModel scheduleInfoModel = new ScheduleInfoModel();
		String scheduleMapper = "client.mapper.ScheduleMapper.";
		
		// scheduleInfoModel select
		Map<String, String> confirmData = new HashMap<String, String>();
		confirmData.put("store_seq", seq);
		String user_seq = (String)request.getSession().getAttribute("user_seq");
		confirmData.put("user_seq", user_seq);
		
		// UserInfoModel select
		String userMapper = "user.mapper.UserMapper.";
		UserInfoModel userInfoModel = new UserInfoModel();
		userInfoModel.setUser_seq(user_seq);
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			scheduleInfoModel = session.selectOne(scheduleMapper + "select_temporary_schedule_one", confirmData);
			userInfoModel = session.selectOne(userMapper + "select_userInfo", userInfoModel);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}

		
		// 상품정보의 마지막 시간은 계산.
		String[] start_time = scheduleInfoModel.getSchedule_hm().split(":");
		String[] play_time = scheduleInfoModel.getMenu_time().split(":");
		int[] last_time = {Integer.parseInt(start_time[0]) + Integer.parseInt(play_time[0]), Integer.parseInt(start_time[1]) + Integer.parseInt(play_time[1])};
		if(last_time[1] > 60){last_time[1] -= 60;last_time[0] += 1;}
		String time = "";
		String time1 = "";
		String time2 = "";
		if(last_time[0] < 10){time1 = "0" + Integer.toString(last_time[0]);}else{time1 = Integer.toString(last_time[0]);}
		if(last_time[1] < 10){time2 = "0" + Integer.toString(last_time[1]);}else{time2 = Integer.toString(last_time[1]);}
		time = time1 + ":" + time2;
		scheduleInfoModel.setEnd_time(time);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/store_reservation_confirm");
		mav.addObject("scheduleInfoModel", scheduleInfoModel);
		mav.addObject("userInfoModel", userInfoModel);
		return mav;
	}
	
}
