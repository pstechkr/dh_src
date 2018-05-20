package client.method;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import mysql.conn.MySqlConn;

import org.apache.ibatis.session.SqlSession;

import client.model.CalendarInfoModel;
import client.model.MenuInfoModel;
import client.model.StoreInfoModel;
import tiein.method.TieinCalendar;


public class EleverCalendar extends TieinCalendar {
	
	public TreeMap <String, CalendarInfoModel> customRsvInfo(int YEAR, int MONTH, StoreInfoModel storeInfoModel, TreeMap <String, CalendarInfoModel> calendarMap)
	{
		// db input model setting
		// db에서 해당 월에 커스텀 예약정보가 있는지 찾아온다.
		HashMap<String, Object> customRsvInput = new HashMap<String, Object> ();
//		customRsvInput.put("client_seq", Integer.parseInt(storeInfoModel.getClient_seq()));
		customRsvInput.put("store_seq", Integer.parseInt(storeInfoModel.getStore_seq()));
		customRsvInput.put("custom_rsv_year", YEAR);
		customRsvInput.put("custom_rsv_month", MONTH);
		
		// db output setting
		Map<Object, Map<String, String>> customRsvMap = null;
		
		// db setting
		String storeMapper = "client.mapper.StoreMapper.";
		String customRsvMapper = "client.mapper.CustomRsvMapper.";
		
		try{
			MySqlConn conn = new MySqlConn();
			SqlSession session = conn.openSession();
			
			try{
				// 정기휴무일 정보, 커스텀 예약날짜 정보를 검색해온다. 
				storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);
				customRsvMap = session.selectMap(customRsvMapper + "select_customRsvInfo", customRsvInput, "custom_rsv_ymd");

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		// 현재 reservation_switch에는 전부 true값이 들어가 있는 상태.
		// 정기 휴무일 * dayoff[0] 일요일, dayoff[1] 월요일, dayoff[2] 화요일, dayoff[3] 수요일, dayoff[4] 목요일, dayoff[5] 금요일, dayoff[6] 토요일
		String[] dayoff = storeInfoModel.getStore_dayoff().split("/");
		
		for(Map.Entry<String, CalendarInfoModel> entry: calendarMap.entrySet())
		{
			if(dayoff[entry.getValue().getDAY()-1].equals("1")){
				entry.getValue().setRESERVATION_SWITCH(false);
			}
		}
		
		// 커스템 예약날짜 정보 중에서 key가 있는 날, switch가 1이면 true;
		for(Map.Entry<Object, Map<String, String>> entry: customRsvMap.entrySet())
		{
			if(entry.getValue().get("custom_rsv_switch").equals("1")){
				calendarMap.get(entry.getKey()).setRESERVATION_SWITCH(true);
			
			}else if(entry.getValue().get("custom_rsv_switch").equals("0")){
				calendarMap.get(entry.getKey()).setRESERVATION_SWITCH(false);
			}
			
			System.out.println("KEY : " + entry.getKey() + " / VALUE : " + entry.getValue());
		}

		return calendarMap;
	}
	
	
	
	
	
	// 상품 정보를 불러와서 캘린더에 예약 가능 불가능 여부를 체크한다.
	public TreeMap <String, CalendarInfoModel> menuRsvInfo(String store_seq, String menu_seq, TreeMap <String, CalendarInfoModel> calendarMap){
		
		MenuInfoModel menuInfoModel = new MenuInfoModel();
		menuInfoModel.setStore_seq(store_seq);
		menuInfoModel.setMenu_seq(menu_seq);
		
		System.out.println("menuRsvInfo) store_seq : " + menuInfoModel.getStore_seq());
		System.out.println("menuRsvInfo) menu_seq : " + menuInfoModel.getMenu_seq());
		
		// db setting
		String menuMapper = "client.mapper.MenuMapper.";
		
		try{
			MySqlConn conn = new MySqlConn();
			SqlSession session = conn.openSession();
			
			try{
				// 정기휴무일 정보, 커스텀 예약날짜 정보를 검색해온다.
				menuInfoModel = session.selectOne(menuMapper + "select_menuInfo_one", menuInfoModel);

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 해당 상품 판매불가일체크 1:예약가능 0: 예약불가.
		// * dayoff[0] 일요일, dayoff[1] 월요일, dayoff[2] 화요일, dayoff[3] 수요일, dayoff[4] 목요일, dayoff[5] 금요일, dayoff[6] 토요일
		String[] dayoff = menuInfoModel.getMenu_day().split("/");
		
		for(Map.Entry<String, CalendarInfoModel> entry: calendarMap.entrySet())
		{
			if(dayoff[entry.getValue().getDAY()-1].equals("0")){
				entry.getValue().setRESERVATION_SWITCH(false);
			}
		}
	
		return calendarMap;
	}
	
	
	
	
	// 오늘보다 이전 날이면 비활성화로 체크를 바꾼다.
	public TreeMap <String, CalendarInfoModel> beforeCheck(int YEAR, int MONTH, int DATE, TreeMap <String, CalendarInfoModel> calendarMap)
	{
		for(Map.Entry<String, CalendarInfoModel> entry: calendarMap.entrySet())
		{
			int _YEAR = Integer.parseInt(calendarMap.get(entry.getKey()).getYEAR());
			int _MONTH = Integer.parseInt(calendarMap.get(entry.getKey()).getMONTH());
			int _DATE = Integer.parseInt(calendarMap.get(entry.getKey()).getDATE());
			
			// 이전 년도
			if(_YEAR < YEAR){
				calendarMap.get(entry.getKey()).setTHIS_MONTH(false);
			}else{
				
				// 이전 달
				if(_MONTH < MONTH){
					calendarMap.get(entry.getKey()).setTHIS_MONTH(false);
				}
				// 이번 달 중에서 이전 일
				else if(_MONTH == MONTH){
					if(_DATE < DATE){
						calendarMap.get(entry.getKey()).setTHIS_MONTH(false);
					}
				}
			}
		}
		return calendarMap;
	}
	
	
	
//	// 예약 가능/불가능을 불러오는 메서드 (미리 해당 캘린더를 불러와서 파라미터에 넣어야 한다.)
//	public CalendarInfoModel[][] customRsvInfo(int YEAR, int MONTH, CalendarInfoModel[][] calendarList, StoreInfoModel storeInfoModel)
//	{	
//		// db input model setting
//		HashMap<String, Object> customRsvInput = new HashMap<String, Object> ();
//		customRsvInput.put("client_seq", Integer.parseInt(storeInfoModel.getClient_seq()));
//		customRsvInput.put("store_seq", Integer.parseInt(storeInfoModel.getStore_seq()));
//		customRsvInput.put("custom_rsv_year", YEAR);
//		customRsvInput.put("custom_rsv_month", MONTH);
//		
//		// db output setting
//		Map<Object, Map<String, String>> customRsvMap = null;
//		
//		// 만들어진 캘린더 배열에 DB에서 불러온 데이터를 찾아 함께 넣어준다.
//		// db setting
//		String storeMapper = "client.mapper.StoreMapper.";
//		String customRsvMapper = "client.mapper.CustomRsvMapper.";
//		
//		try
//		{
//			MySqlConn conn = new MySqlConn();
//			SqlSession session = conn.openSession();
//			
//			try
//			{
//				// 정기휴무일 정보, 커스텀 예약날짜 정보를 검색해온다. 
//				storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);
//				customRsvMap = session.selectMap(customRsvMapper + "select_customRsvInfo", customRsvInput, "custom_rsv_ymd");
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//				session.close();
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		System.out.println(customRsvMap.toString());
//		/*
//		 * 예약가능 / 불가능을 배치한다. 
//		 */
//		
//		// 정기휴무일 정보 * dayoff[0] 일요일, dayoff[1] 월요일, dayoff[2] 화요일, dayoff[3] 수요일, dayoff[4] 목요일, dayoff[5] 금요일, dayoff[6] 토요일
//		String[] dayoff = storeInfoModel.getStore_dayoff().split("/");
//
//		// calendar배열에 넣는다.
//		for( int i = 0 ; i < calendarList.length; i++)
//		{
//			for(int j = 0 ; j < 7 ; j++)
//			{
//				if(dayoff[j].equals("0"))
//				{calendarList[i][j].setRESERVATION_SWITCH(true);}
//				else
//				{calendarList[i][j].setRESERVATION_SWITCH(false);}
//				
//				// customRsvMap에 해당 날짜의 키가 잡혀있다면
//				if(customRsvMap.containsKey(calendarList[i][j].getYMD())){
//				
//					// data는 해당 날짜에 해당하는 CustomRsvInfoModel. get(KEY)로 해당 데이터를 가져온다. 
//					Map<String, String> data = customRsvMap.get(calendarList[i][j].getYMD());
//					
//					// 개별 예약기능 ON / OFF - 1:ON, 2:OFF
//					if(data.get("custom_rsv_switch").equals("0")){
//						calendarList[i][j].setRESERVATION_SWITCH(false);
//					}else{
//						calendarList[i][j].setRESERVATION_SWITCH(true);
//					}
//				}
//			}
//		}
//		
//		return calendarList;
//	}
//	
	
	
	
	public void CustomRsvSwitch(int YEAR, int MONTH, StoreInfoModel storeInfoModel, boolean Switch)
	{
		
		// db input model setting
		HashMap<String, Object> customRsvInput = new HashMap<String, Object> ();
		customRsvInput.put("client_seq", Integer.parseInt(storeInfoModel.getClient_seq()));
		customRsvInput.put("store_seq", Integer.parseInt(storeInfoModel.getStore_seq()));
		customRsvInput.put("custom_rsv_year", YEAR);
		customRsvInput.put("custom_rsv_month", MONTH);
		
		// db setting
		String storeMapper = "client.mapper.StoreMapper.";
		// 스토어 정보를 불러온다.
		try
		{
			MySqlConn conn = new MySqlConn();
			SqlSession session = conn.openSession();
			
			try
			{
				// 정기휴무일 정보, 커스텀 예약날짜 정보를 검색해온다. 
				storeInfoModel = session.selectOne(storeMapper + "select_storeInfo", storeInfoModel);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				session.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		if(Switch){
			
			
		}
		else{
			
			
		}	
	}
}
