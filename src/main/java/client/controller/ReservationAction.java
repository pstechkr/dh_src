package client.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import client.method.EleverCalendar;
import client.model.CalendarInfoModel;
import client.model.CustomRsvInfoModel;
import client.model.MenuInfoModel;
import client.model.StoreInfoModel;

@Controller
@RequestMapping("/client")
public class ReservationAction {
	
	// AJAX 캘린더 요청 URL
	@RequestMapping(value = {"/calendarRequest"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> calendarRequest(@RequestBody CalendarInfoModel calendarInfoModel, HttpSession httpSession){
		
		// 캘린더를 가져오기 위해 클라이언트 정보를 불러온다.
		StoreInfoModel storeInfoModel = new StoreInfoModel();
//		storeInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		storeInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		
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

		// 캘린더 맵을 불러온다. ------->  KEY : yyyy-MM-dd   VALUE : CalendarInfoModel
		TreeMap <String, CalendarInfoModel> calendarMap = new TreeMap <String, CalendarInfoModel> ();
		calendarMap = eleverCalendar.calendarLoad(cal);
		
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
		
		// 리턴
		return rtnList;
	}

	// AJAX 예약스위치 요청 URL
	@RequestMapping(value = "/reservationSwitch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> reservationSwitch(@RequestBody CustomRsvInfoModel customRsvInfoModel, HttpSession httpSession)
	{
		
		// StoreInfoModel setting
		StoreInfoModel storeInfoModel = new StoreInfoModel();
		storeInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		storeInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		
		// CustomRsvInfoModel setting
		customRsvInfoModel.setClient_seq((String)httpSession.getAttribute("client_seq"));
		customRsvInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));
		
		// db setting
		//String storeMapper = "client.mapper.StoreMapper.";
		String customRsvMapper = "client.mapper.CustomRsvMapper.";
		String custom_rsv_switch = "";
	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			//storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);
			// 이미 값이 저장되어 있는 경우 Y이면 1, N이면 0의 값이 나올 것이고, 값이 없는 경우 null이 나옴. 
			custom_rsv_switch = session.selectOne(customRsvMapper + "select_customRsvInfoYN", customRsvInfoModel); 
			
			// null인 경우 insert.
			if(custom_rsv_switch == null){
				session.insert(customRsvMapper + "insert_customRsvInfo", customRsvInfoModel);
				System.out.println("insert");
			}
			
			else if(custom_rsv_switch != customRsvInfoModel.getCustom_rsv_switch()){
				session.update(customRsvMapper + "update_customRsvInfo", customRsvInfoModel);
			}else{
				// 값이 있는 경우엔 delete 처리.
				session.delete(customRsvMapper + "delete_customRsvInfo", customRsvInfoModel);
			}
			
			
			// 같은 경우에는 아무 일도 안일어난다.
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<Object> rtnList = new ArrayList<Object>();
		return rtnList;
		
	}	
	// 예약상품관리
	@RequestMapping(value = "/menuRequest", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> menuRequest(HttpSession httpSession)
	{
		// 상품 정보를 불러온다.
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		menuInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));	
		List<MenuInfoModel> menuList = null; 
		// db setting
		String MenuMapper = "client.mapper.MenuMapper.";
		
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		try
		{
			menuList = session.selectList(MenuMapper + "select_menuInfo", menuInfoModel);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		
		List<Object> rtnList = new ArrayList<Object>();
		rtnList.add(menuList);
		return rtnList;
	}

	// 예약상품 신규 등록
	@RequestMapping(value = "/createMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> createMenu(@RequestBody MenuInfoModel menuInfoModel, HttpSession httpSession)
	{
		
		// model setting
		menuInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));

		// db setting
		String MenuMapper = "client.mapper.MenuMapper.";

	
		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			session.insert(MenuMapper + "insert_menuInfo", menuInfoModel);
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<Object> rtnList = new ArrayList<Object>();
		return rtnList;
	}
	
	// 예약상품 수정 URL
	@RequestMapping(value = "/modifyMenu", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody List<Object> modifyMenu(@RequestBody MenuInfoModel menuInfoModel, HttpSession httpSession)
	{
		
		// model setting
		menuInfoModel.setStore_seq((String)httpSession.getAttribute("store_seq"));

		// db setting
		String MenuMapper = "client.mapper.MenuMapper.";

		MySqlConn conn = new MySqlConn();
		SqlSession session = conn.openSession();
		
		try
		{
			session.update(MenuMapper + "update_menuInfoModify", menuInfoModel);
			session.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		
		List<Object> rtnList = new ArrayList<Object>();
		return rtnList;
	}
	
}
