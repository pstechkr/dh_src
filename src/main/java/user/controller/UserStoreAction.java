package user.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import client.method.EleverCalendar;
import client.method.EleverSchedule;
import client.model.CalendarInfoModel;
import client.model.MenuInfoModel;
import client.model.ScheduleInfoModel;
import client.model.StoreInfoModel;
import client.model.TimeTableModel;

@Controller
@RequestMapping("/store/{seq}")
public class UserStoreAction {
	
	// AJAX 캘린더 요청 URL
	@RequestMapping(value = {"/menuCalendarRequest"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> menuCalendarRequest(@RequestBody HashMap<String, String> map, @PathVariable("seq") String store_seq, HttpSession httpSession){
		
		/*
		 * 예약하기 > 캘린더 로드
		 */
		
		CalendarInfoModel calendarInfoModel = new CalendarInfoModel();
		if(map.get("year") != null){
			calendarInfoModel.setYEAR(map.get("year"));
		}
		if(map.get("month") != null){
			calendarInfoModel.setMONTH(map.get("month"));
		}
		
		// 캘린더를 가져오기 위해 클라이언트 정보를 불러온다.
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setStore_seq(store_seq);
		
		// calendar, reservation model setting
		EleverCalendar eleverCalendar = new EleverCalendar();

		// 캘린더 생성
		Calendar cal = Calendar.getInstance(); // 캘린더를 하나 끌어와서
		
		// 오늘 날짜를 미리 빼둔다.
		int TODAY_YEAR = cal.get(Calendar.YEAR);
		int TODAY_MONTH = cal.get(Calendar.MONTH) + 1;
		int TODAY_DATE = cal.get(Calendar.DATE);
		
		// 그리고, 1일로 셋팅 (기준)
		if(calendarInfoModel.getYEAR() == null){
			cal.set(Calendar.DATE, 1);
		}else{
			cal.set(Integer.parseInt(calendarInfoModel.getYEAR()), Integer.parseInt(calendarInfoModel.getMONTH())-1,1); // 1일로 셋팅 (기준)
		}
		
		System.out.println("불러오는 캘린더의 연도/월 : " + calendarInfoModel.getYEAR() + "    " + calendarInfoModel.getMONTH());

		
		// 캘린더 맵을 불러온다. ------->  KEY : yyyy-MM-dd   VALUE : CalendarInfoModel
		TreeMap <String, CalendarInfoModel> calendarMap = new TreeMap <String, CalendarInfoModel> ();
		calendarMap = eleverCalendar.calendarLoad(cal);
		
		// 해당 상품의 예약 가능, 불가능 여부를 판단하여 calendarMap에 데이터를 넣는다.
		calendarMap = eleverCalendar.menuRsvInfo(store_seq, map.get("menu_seq"), calendarMap);
		
		// 예약불가능 여부를 판단하여 calendarMap에 데이터를 넣는다.
		calendarMap = eleverCalendar.customRsvInfo(eleverCalendar.getSTANDARD_YEAR(), eleverCalendar.getSTANDARD_MONTH(), storeInfoModel, calendarMap);
		
		// 오늘보다 이전 날이면 비활성화로 체크를 바꾼다.
		calendarMap = eleverCalendar.beforeCheck(TODAY_YEAR, TODAY_MONTH, TODAY_DATE, calendarMap);
		
		// return을 위해 배열로 변경한다.
		CalendarInfoModel[][] calList = eleverCalendar.MapToMatrix(calendarMap);
	
		// JSON 정렬
		List<Object> rtnList = new ArrayList<Object>();
		rtnList.add(eleverCalendar.getSTANDARD_YEAR());
		rtnList.add(eleverCalendar.getSTANDARD_MONTH());
		rtnList.add(calList);
//		rtnList.add(menuInfoModel);
		System.out.println(calList);
		
		// 리턴
		return rtnList;
	}
	
	// 스케줄 :: 예약 가능한 타임테이블
	@RequestMapping(value = "/timetableRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> TimeTableRequest(@RequestBody HashMap<String, String> map, @PathVariable("seq") String store_seq, HttpSession httpsession){
	
		ScheduleInfoModel scheduleInfoModel = new ScheduleInfoModel();
		scheduleInfoModel.setSchedule_year(map.get("schedule_year"));
		scheduleInfoModel.setSchedule_month(map.get("schedule_month"));
		scheduleInfoModel.setSchedule_date(map.get("schedule_date"));
		scheduleInfoModel.setStore_seq(store_seq);
		
		int member = Integer.parseInt(map.get("member")); // 예약신청인원수.
		
		List<ScheduleInfoModel> scheduleList = new ArrayList<ScheduleInfoModel>();

		String ScheduleMapper = "client.mapper.ScheduleMapper.";
		String StoreMapper = "client.mapper.StoreMapper.";
		String MenuMapper = "client.mapper.MenuMapper.";
		EleverSchedule eleverSchedule = new EleverSchedule();
		
		// 영업시간을 추출하기 위해 storeInfoModel 로드
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setStore_seq(store_seq);

		// 해당 메뉴의 시간을 추출하기 위해 menuInfo 로드
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		menuInfoModel.setStore_seq(store_seq);
		menuInfoModel.setMenu_seq(map.get("menu_seq"));
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			scheduleList = session.selectList(ScheduleMapper + "select_scheduleTimeTable", scheduleInfoModel);
			storeInfoModel = session.selectOne(StoreMapper + "select_reservationInfo", storeInfoModel);
			menuInfoModel = session.selectOne(MenuMapper + "select_menuInfo_one", menuInfoModel);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		// 시간, 방문예정, 예약진행을 리스트로 정리하는 로직
		int divideMin = 30;
		Map<String, TimeTableModel> table = new TreeMap<String, TimeTableModel>();
		table = eleverSchedule.getTimetable(scheduleList, divideMin);
		// table -->> 현재 스케줄표를 바탕으로 방문예정  /  예약진행 인원을 뽑아온 리스트
		
		// 상점이 지정한 예약 가능시간과 이용자가 선택한 메뉴의 시간을 더하여 타임테이블 제작
		Map<String, TimeTableModel> userTable = new TreeMap<String, TimeTableModel>();
		userTable = eleverSchedule.getUsertimetable(table, storeInfoModel, menuInfoModel, divideMin, member);

		// LIST로 변경
		List<TimeTableModel> tableList = new ArrayList<TimeTableModel>();
		int index = 0;
		for(Map.Entry<String, TimeTableModel> entry: userTable.entrySet()){
			tableList.add(index, entry.getValue());
			index++;
		}

		// JSON 정렬
		Map<String, Object> rtnList = new HashMap<String, Object>();
		
		// 리턴
		rtnList.put("tableList", tableList);
		return rtnList;
	}

	// 예약신청 :: 로그인 세션 체크
	@RequestMapping(value = "/loginSessionCheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> loginSessionCheck(HttpSession httpsession){

		String sessionCheck = "N";
		if(httpsession.getAttribute("user_seq") != null){
			sessionCheck = "Y";
		}
		
		// JSON 정렬
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("sessionCheck", sessionCheck);
		return rtnMap;
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
		}
		return rtnMap;
	}
	
	
	// 예약신청 :: 상품 예약 확인 액션.
	// 이 로직이 타기 전, 로그인을 하는 부분이 있음. 
	// 로그인이 된 상태로 해당 로직을 타게 됨. 하지만 request 데이터는 로그인이 되기 전의 데이터가 들어올 수도 있음.
	// 즉, A가 이미 신청을 해둔 상품인데 confirm을 안했고, 또 로그인 안하고 신청할 경우 중복 발생. 
	// @RequestMapping("/reservation") 에서처럼, 중복체크 한번 필요함.
	
	@RequestMapping("/confirmRequest")
	public ModelAndView confirmRequest(@PathVariable("seq") String seq, HttpServletRequest request, @ModelAttribute ScheduleInfoModel scheduleInfoModel){
		
		// MenuInfoModel
		String menuMapper = "client.mapper.MenuMapper.";
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		String menu_seq = scheduleInfoModel.getMenu_seq();
		menuInfoModel.setMenu_seq(menu_seq);
		menuInfoModel.setStore_seq(seq);
		
		// insert
		String scheduleMapper = "client.mapper.ScheduleMapper.";
		scheduleInfoModel.setStore_seq(seq);
		String user_seq = (String)request.getSession().getAttribute("user_seq");
		scheduleInfoModel.setUser_seq(user_seq);
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			// /reservation 로직 복붙.
			// (2) 5분 이내에 있는 temporary_schedule Check (로그인 되어있어야 함)
			// 이 경우는 항상 로그인이 되어있으므로 해당 로직을 항상 탄다.
			
			Map<String, String> temporaryCheck = new HashMap<String, String>();
			temporaryCheck.put("store_seq", seq);
			temporaryCheck.put("user_seq", user_seq);
			
			Map<String, String> temporaryModel = new HashMap<String, String>();
			temporaryModel = session.selectOne(scheduleMapper + "select_temporary_schedule", temporaryCheck);
			
			// 5분 이내 confirm 하지 않은 temporary_schedule이 존재한다면,
			if(temporaryModel.get("isCheck").equals("Y")){

				// javascript에서 Y/N 묻고 삭제하는 request 혹은 redirect. 
				ModelAndView temporaryMav = new ModelAndView("alert_confirm");
				temporaryMav.addObject("msg", "이미 예약 진행중인 상품이 있습니다.\\n확인을 누르면 기존의 예약을 취소하고 현재 예약을 이어서 진행 합니다.");
				temporaryMav.addObject("func", "location.href='" + request.getSession().getServletContext().getContextPath() + "/" + seq + "/confirm';");
				temporaryMav.addObject("schedule_seq",temporaryModel.get("schedule_seq"));
				temporaryMav.addObject("divide", "divide");
				temporaryMav.addObject("scheduleInfoModel", scheduleInfoModel);
				return temporaryMav;
			}
			
			// 메뉴모델 로드하여 schedule에 복사.
			menuInfoModel = session.selectOne(menuMapper + "select_menuInfo_one", menuInfoModel);
			scheduleInfoModel.setMenu_name(menuInfoModel.getMenu_name());
			scheduleInfoModel.setMenu_instruction(menuInfoModel.getMenu_instruction());
			scheduleInfoModel.setMenu_time(menuInfoModel.getMenu_time());
			scheduleInfoModel.setMenu_price(menuInfoModel.getMenu_price());
			
			// database에 담기.
			
			System.out.println(scheduleInfoModel.getSchedule_ymd());
			
			session.insert(scheduleMapper + "insert_scheduleInfoRegister", scheduleInfoModel);
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		return new ModelAndView(new RedirectView(request.getSession().getServletContext().getContextPath() + "/" + seq + "/confirm"));
	}
	
	// 예약신청 :: 상품 예약 확인 페이지에서 예약 확정.
	@RequestMapping("/confirmCommit")
	public ModelAndView confirmCommit(@PathVariable("seq") String seq, HttpServletRequest request){
		
		
		String scheduleMapper = "client.mapper.ScheduleMapper.";
		String schedule_seq = request.getParameter("schedule_seq");
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			session.update(scheduleMapper + "update_temporary_schedule", schedule_seq);
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		return new ModelAndView(new RedirectView(request.getSession().getServletContext().getContextPath() + "/" + seq + "/reservation"));
	}
	
	// 예약신청 :: 5분 지나기 전 삭제요청 후 reservation 리로드.
	@RequestMapping("/deleteConfirm")
	public ModelAndView deleteConfirm(@PathVariable("seq") String seq, HttpServletRequest request, @ModelAttribute ScheduleInfoModel scheduleInfoModel){

		String scheduleMapper = "client.mapper.ScheduleMapper.";
		String schedule_seq = request.getParameter("schedule_seq");

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();

		try
		{
			session.delete(scheduleMapper + "delete_temporary_schedule_by_user", schedule_seq);
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		
		// confirmRequest에서 들어온 로직인 경우 scheduleInfoModel에 값이 포함되어 있다.
		// 이 경우 받아온 scheduleInfoModel을 넣어서 confirmRequest를 한번 더 실행.
		if(!scheduleInfoModel.getMenu_seq().equals("")){
			System.out.println("scheduleInfoModel타는가?");
			return confirmRequest(seq, request, scheduleInfoModel);
		}
		
		return new ModelAndView(new RedirectView(request.getSession().getServletContext().getContextPath() + "/" + seq + "/reservation"));
	}
}