package client.method;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import client.model.MenuInfoModel;
import client.model.ScheduleInfoModel;
import client.model.StoreInfoModel;
import client.model.TimeTableModel;

public class EleverSchedule {

	public Map<String, TimeTableModel> getTimetable(List<ScheduleInfoModel> scheduleList, int divideMin){

		// scheduleList를 불러와서 각 schedule마다 시작시간과 진행시간을 체크한다. 
		// (ex) 11:00 시작 13:00 종료인 경우 Map에 11:00, 11:30, 12:00, 12:30, 13:00 까지 KEY가 생성되고
		// (KEY 중복은 생성 안되고 데이터만 변함)
		// 시작 시간에는 ready에 인원이 들어가고, 진행시간에는 doing에 인원이 들어감.
		
		Map<String, TimeTableModel> table = new TreeMap<String, TimeTableModel>();

		// 리스트를 불러와서 scheduleList에 있는 시간 쪼개기 (11:00면 11:00.. 11:30.. 12:30..) 시작
		for(int i = 0 ; i < scheduleList.size(); i++){
			
			String[] _startTime = scheduleList.get(i).getSchedule_hm().split(":");
			String[] _menuTime = scheduleList.get(i).getMenu_time().split(":");

			int startHour = Integer.parseInt(_startTime[0]);
			int startMin = Integer.parseInt(_startTime[1]);
			
			int menuHour = Integer.parseInt(_menuTime[0]);
			int menuMin = Integer.parseInt(_menuTime[1]);
			
			int endHour = startHour + menuHour;
			int endMin = startMin + menuMin;
			if(endMin >= 60){endHour ++;endMin -= 60;}
			
			// 그런데 마지막 시간에 인원이 들어가면 안되므로 divideMin을 뺀다.
			endMin -= divideMin;
			if(endMin < 0){	endHour --; endMin += 60; }
			
			int count = 1;
			
			// 각 하나의 상품별로 while을 돌기 시작. for는 전체 예약 하나하나를 뜻하고 while은 개별상품의 시간을 쪼개기 위함.
			while(startHour <= endHour){
				// logic
				if(count > 1){
					if(startHour == endHour){
						if(endMin < startMin + divideMin){
							break;
						}
					}
					startMin += divideMin;
					if(startMin >= 60){
						startHour++;
						startMin -= 60;
					}
				}
				String hour ="";
				String minute ="";
				if(startHour < 10){hour = "0" + Integer.toString(startHour); }else{hour = Integer.toString(startHour);}
				if(startMin < 10){minute = "0" + Integer.toString(startMin);}else{minute = Integer.toString(startMin);}
				
				// 여기서부터 데이터를 넣기 시작.
				// KEY가 예약 상품의 시작시간부터 끝시간이 될 것.
				String KEY = hour + ":" + minute;
				
				// count가 1이면 시작시간이라는 뜻. (Ready) 그 외에는 (Doing)
				if(count == 1){
					
					// 키가 없는 경우 TimeTableModel을 새로 생성하여 put.
					if(!table.containsKey(KEY)){
						TimeTableModel tmp = new TimeTableModel();
						tmp.setReadyInt(Integer.parseInt(scheduleList.get(i).getMember()));
						// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
						tmp.setReady(scheduleList.get(i).getSchedule_seq() + "/");
						// reservation 기본 세팅은 true
						tmp.setReservation(true);
						// 최대인원값 셋팅
						tmp.setMax(Integer.parseInt(scheduleList.get(i).getMember()));
						tmp.setTime(KEY);
						
						table.put(KEY, tmp);
					}
					else{
						// 키가 있는 경우 이미 있는 Map안의 TimeTableModel을 변경.
						if(table.get(KEY).getReady() == null){
							// 이미 키가 생성되어 있는 table 안에 ready 데이터가 없으면 null이 들어있는데, 그냥 안에 있는 내용을 가져와서 seq를 추가하면 null+x/ 가 되어버므로.
							table.get(KEY).setReadyInt(Integer.parseInt(scheduleList.get(i).getMember()));
							// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
							table.get(KEY).setReady(scheduleList.get(i).getSchedule_seq() + "/");
						
						}else{
							// null이 아닌 경우에는 있는 것에 추가하여 넣기
							table.get(KEY).setReadyInt(table.get(KEY).getReadyInt() + Integer.parseInt(scheduleList.get(i).getMember()));
							// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
							table.get(KEY).setReady(table.get(KEY).getReady() + scheduleList.get(i).getSchedule_seq() + "/");
						}
						table.get(KEY).setMax(table.get(KEY).getMax() + Integer.parseInt(scheduleList.get(i).getMember()));
					}
				} // count = 1 if end
				// DOING을 넣는 부분
				else{
				
					// 위와 같으면서 Doing으로 변경한 것.
					// 키가 없는 경우 TimeTableModel을 새로 생성하여 put.
					if(!table.containsKey(KEY)){
						TimeTableModel tmp = new TimeTableModel();
						tmp.setDoingInt(Integer.parseInt(scheduleList.get(i).getMember()));
						// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
						tmp.setDoing(scheduleList.get(i).getSchedule_seq() + "/");
						// reservation 기본 세팅은 true
						tmp.setReservation(true);
						// 최대인원값 셋팅
						tmp.setMax(Integer.parseInt(scheduleList.get(i).getMember()));
						tmp.setTime(KEY);
						
						table.put(KEY, tmp);
					}
					else{
						// 키가 있는 경우 이미 있는 Map안의 TimeTableModel을 변경.
						if(table.get(KEY).getDoing() == null){
							// 이미 키가 생성되어 있는 table 안에 Doing 데이터가 없으면 null이 들어있는데, 그냥 안에 있는 내용을 가져와서 seq를 추가하면 null+x/ 가 되어버므로.
							table.get(KEY).setDoingInt(Integer.parseInt(scheduleList.get(i).getMember()));
							
							// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
							table.get(KEY).setDoing(scheduleList.get(i).getSchedule_seq() + "/");
						
						}else{
							// null이 아닌 경우에는 있는 것에 추가하여 넣기
							table.get(KEY).setDoingInt(table.get(KEY).getDoingInt() + Integer.parseInt(scheduleList.get(i).getMember()));
							
							// 방문예정명단, 예약진행명단을 불러오기 위해 미리 seq를 확보한다.
							table.get(KEY).setDoing(table.get(KEY).getDoing() + scheduleList.get(i).getSchedule_seq() + "/");
						}
						// 최대인원값 추가
						table.get(KEY).setMax(table.get(KEY).getMax() + Integer.parseInt(scheduleList.get(i).getMember()));
					}
				}
				
				
				count++;
			} // while end
		} // scheduleList for end 
		
		// return
		return table;
	}
					
	// USER TIMETABLE :: getTimetable에서 불러온 예약체크, 상점주가 지정한 예약가능시간범위, 메뉴의 소요시간을 체크하여 재정렬.
	public Map<String, TimeTableModel> getUsertimetable(Map<String, TimeTableModel> table, StoreInfoModel storeInfoModel, MenuInfoModel menuInfoModel, int divideMin, int member){

		// 사업주가 지정한 예약 최대 인원.
		int reservation_max = Integer.parseInt(storeInfoModel.getReservation_max());
		
		String _startTime[] = storeInfoModel.getReservation_openinghours1().split(":");
		String _endTime[] = storeInfoModel.getReservation_openinghours2().split(":");
		String _menuTime[] = menuInfoModel.getMenu_time().split(":");
		
		int startHour = Integer.parseInt(_startTime[0]);
		int startMin = Integer.parseInt(_startTime[1]);
		int endHour = Integer.parseInt(_endTime[0]);
		int endMin = Integer.parseInt(_endTime[1]);
		int menuHour = Integer.parseInt(_menuTime[0]);
		int menuMin = Integer.parseInt(_menuTime[1]);
		
		// 마지막 예약 가능 시간은 메뉴시간을 빼야한다. ex) 예약이 5시까지고 메뉴시간이 2시간이면 해당 예약은 3시가 최대.
		endHour -= menuHour;
		endMin -= menuMin;
		if(endMin < 0){endHour --;endMin += 60;}
		
		Map<String, TimeTableModel> timeTable = new TreeMap<String, TimeTableModel>();
		
		int count = 1;
		
		// 타임테이블 시간. 
		while(startHour <= endHour){
			// logic
			if(count > 1){
				if(startHour == endHour){
					if(endMin < startMin + divideMin){
						break;
					}
				}
				
				startMin += divideMin;
				if(startMin >= 60){
					startHour++;
					startMin -= 60;
				}
			}
			
			String hour ="";
			String minute ="";
			if(startHour < 10){hour = "0" + Integer.toString(startHour); }else{hour = Integer.toString(startHour);}
			if(startMin < 10){minute = "0" + Integer.toString(startMin);}else{minute = Integer.toString(startMin);}
			
			
			// 여기서부터 데이터를 넣기 시작
			String KEY = hour + ":" + minute;
			
			// 기존의 예약테이블에서 있는 키는 그대로 집어넣고, 없는 것은 tmp 생성하여 시간을 넣음
			
			// 기존의 예약테이블 시간에 방문예정 및 예약진행인원을 더한 값에 현재 예약예정인원을 더할 때, 
			// 사업주가 지정한 최대 예약 인원을 넘을 경우 해당 시간, 해당 시간 앞으로 메뉴시간 만큼은 지워야함.

			if(table.containsKey(KEY)){
				timeTable.put(hour + ":" + minute, table.get(KEY));
			}else{
				TimeTableModel tmp = new TimeTableModel();
				tmp.setTime(KEY);
				tmp.setDoingInt(0);
				tmp.setReadyInt(0);
				tmp.setMax(0);
				tmp.setReservation(true);
				timeTable.put(KEY, tmp);
			}
			count++;
		}
		
		// for문 돌면서 reservation_max를 넘는 시간을 체크한다.
		Map<String, String> temp = new TreeMap<String, String>();
		
		for(Map.Entry<String, TimeTableModel> entry: timeTable.entrySet())
		{
			System.out.println("TIME : " + entry.getValue().getTime());
			System.out.println("MAX : " + entry.getValue().getMax() + "   READY : " + entry.getValue().getReadyInt() + "   DOING : " + entry.getValue().getDoingInt());
			
			// 아직 미완성. 예를들어 2시부터 4시까지 예약을 하려고 하는데, 4시에 새로 시작하는 인원이 있어서 예약이 불가되는 경우가 있음.
			
			int _member = member + entry.getValue().getMax();
			System.out.println("_MEMBER : " + _member);
			System.out.println("RESERVATION MAX : " + reservation_max);
			
			// ex) 예약최대 인원이 8명인데 지금시간에 존재하는 사람에 추가하려는 사람을 더한 값이 9명 이상이라면, 해당 로직을 탐.
			// 아래는 예약 불가능한 시간을 만들어주는 로직.
			if(_member > reservation_max){
				
				// timetable에 있는 시간을 가져와서. 메뉴시간만큼 뺀다.
				String[] _time = entry.getValue().getTime().split(":");
				int reservation_endHour = Integer.parseInt(_time[0]);
				int reservation_endMin = Integer.parseInt(_time[1]);
				int reservation_startHour = reservation_endHour - menuHour; 
				int reservation_startMin = reservation_endMin - menuMin; 
				if(reservation_endMin < 0){reservation_startHour --;reservation_startMin += 60;}
				
				// 그런데 이 경우 timetable에 있는 시간 - 메뉴시간이 <예약가능 시간>보다 -가 되는 경우가 있음
				// ex) 10시 예약불가인데 메뉴시간이 2시간짜리라서 8시로 체크되지만 예약가능시간이 9시부터 시작되는 경우.
				
				// startHour, Min을 다시 첫시간으로 맞춰주고.
				startHour = Integer.parseInt(_startTime[0]);
				startMin = Integer.parseInt(_startTime[1]);
				
				System.out.println("첫시간 : " + startHour + "  " + startMin);
				System.out.println("예약시작시간 : " + reservation_startHour + "  " + reservation_startMin);
				
				if(reservation_startHour <= startHour){
					
					if(reservation_startHour < startHour){
						reservation_startHour = startHour;
						reservation_startMin = startMin;
					}else{
						if(reservation_startMin < startMin){
							reservation_startHour = startHour;
							reservation_startMin = startMin;
						}
					}
				}
				
				count = 1;
				// 예약 불가능한 시간. 
				while(reservation_startHour <= reservation_endHour){
					// logic
					if(count > 1){
						if(reservation_startHour == reservation_endHour){
							if(reservation_endMin < reservation_startMin + divideMin){
								break;
							}
						}
						reservation_startMin += divideMin;
						if(reservation_startMin >= 60){
							reservation_startHour++;
							reservation_startMin -= 60;
						}
					}
					
					String _hour ="";
					String _minute ="";
					if(reservation_startHour < 10){_hour = "0" + Integer.toString(reservation_startHour); }else{_hour = Integer.toString(reservation_startHour);}
					if(reservation_startMin < 10){_minute = "0" + Integer.toString(reservation_startMin);}else{_minute = Integer.toString(reservation_startMin);}
					
					String KEY = _hour + ":" + _minute;
					
					System.out.println("불가능시간 KEY :" + KEY);
					
					
					// 해당 시간과 해당 시간빼기 메뉴시간.
					temp.put(KEY, KEY);
					count++;
				}
			}
		}
		
		// temp안에 든 KEY값은 예약이 불가능한 시간이므로, 원래 타임테이블에서 해당 키값의 내용을 지운다.
		// 근데 temp값이 없을 수도 있으므로..
		
		System.out.println("TEMP : " + temp);
		System.out.println("timeTable : " + timeTable);
		for(Map.Entry<String, String> entry: temp.entrySet())
		{
			timeTable.get(entry.getKey()).setReservation(false);
		}
		
		return timeTable;
	}
}
