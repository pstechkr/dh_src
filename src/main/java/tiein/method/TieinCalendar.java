package tiein.method;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import client.model.CalendarInfoModel;

public class TieinCalendar {

	// 지난 달 마지막 일요일
	private int PREVIOUS_YEAR;
	private int PREVIOUS_MONTH;
	private int PREVIOUS_DATE;
	private int PREVIOUS_DAY;
	
	// 기준 달 첫 날
	private int STANDARD_YEAR;
	private int STANDARD_MONTH;
	private int STANDARD_DATE;
	private int STANDARD_DAY;
	
	// 이번 달 마지막 날
	private int LAST_DATE;
	private int LAST_DAY;
	
	// 다음 달 첫번째 토요일
	private int CLOSING_DATE;
	private int CLOSING_DAY;
	
	private Calendar calendarSet(Calendar cal, String token){
		
		Calendar _cal = (Calendar)cal.clone();
		
		if(token == "previous"){
			
			_cal.add(Calendar.DAY_OF_YEAR, -1); // 달력을 지난 달의 마지막 날짜로 이동시키고
			_cal.set(Calendar.DAY_OF_WEEK, 1); // 마지막 주의 일요일로 셋팅 완료.
			
			PREVIOUS_YEAR = _cal.get(Calendar.YEAR);
			PREVIOUS_MONTH = _cal.get(Calendar.MONTH)+1;
			PREVIOUS_DATE = _cal.get(Calendar.DATE); // 날짜
			PREVIOUS_DAY = _cal.get(Calendar.DAY_OF_WEEK); // 요일 (1~7)
		}
		else if(token == "standard"){
			
			STANDARD_YEAR = _cal.get(Calendar.YEAR);
			STANDARD_MONTH = _cal.get(Calendar.MONTH)+1;
			STANDARD_DATE = _cal.get(Calendar.DATE);
			STANDARD_DAY = _cal.get(Calendar.DAY_OF_WEEK);
			
		}
		else if(token == "last"){
			
			_cal.add(Calendar.MONTH, +1); // 이번 달에 한 달을 더한 뒤,
			_cal.add(Calendar.DAY_OF_YEAR, -1); // 하루를 빼서 이번 달의 마지막 날을 구한다.
			
			LAST_DATE = _cal.get(Calendar.DATE); 
			LAST_DAY = _cal.get(Calendar.DAY_OF_WEEK);
			
		}
		else if(token == "closing"){
			if(LAST_DAY != 7)
			{
				_cal.add(Calendar.MONTH, +1); // 이번 달에 한 달을 더한 뒤,
				_cal.set(Calendar.DAY_OF_WEEK, 7); // 첫 주의 토요일로 셋팅 완료.
				
				CLOSING_DATE = _cal.get(Calendar.DATE); 
				CLOSING_DAY = _cal.get(Calendar.DAY_OF_WEEK);
			}
		}
		else{
			System.out.println("bug..?");
		}
		
		return _cal;
	}
	
	// HashMap에 이번달 정보를 담는다. 
	public TreeMap<String, CalendarInfoModel> calendarLoad(Calendar cal){
		
		Calendar standardCal = calendarSet(cal, "standard");
		Calendar previousCal = calendarSet(cal, "previous");
		Calendar lastCal = calendarSet(cal, "last");
		Calendar closingCal = calendarSet(cal, "closing");
		
		//5주차인지 6주차인지 판단하여 2차원 배열을 만든다.
		int WEEK = (STANDARD_DAY + LAST_DATE + CLOSING_DATE)/7;

		TreeMap <String, CalendarInfoModel> calendarMap = new TreeMap <String, CalendarInfoModel>();
		// calendarInfo에 넣을 날짜를 만든다.
		CalendarInfoModel calendarInfoModel = new CalendarInfoModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// for문 돌리기 전 변수 선언. 개별날짜, 개별요일
		String _YMD = "";
		int _DAY;
		int k = 1;
		for(int i = 0 ; i < (WEEK * 7); i++ )
		{
			if ( k < STANDARD_DAY )
			{
				_YMD = dateFormat.format(previousCal.getTime());
				_DAY = previousCal.get(Calendar.DAY_OF_WEEK);
				// 년,월,일,요일,주차 등의 일반정보를 담는다. 함수정보 참고.
				calendarInfoModel = setCalendarInfo(_YMD, _DAY);
				// 셋팅이 끝나면 날짜도 다음날로 옮긴다.
				previousCal.add(Calendar.DAY_OF_YEAR, 1);
			}
			else{
				_YMD = dateFormat.format(standardCal.getTime());
				_DAY = standardCal.get(Calendar.DAY_OF_WEEK);
				calendarInfoModel = setCalendarInfo(_YMD, _DAY);
				standardCal.add(Calendar.DAY_OF_YEAR, 1);
			}
			// 예약가능여부를 true로 만든다. (기본값)
			calendarInfoModel.setRESERVATION_SWITCH(true);
			calendarMap.put(_YMD, calendarInfoModel);
		}
		
		return calendarMap;
	}
	
	// 리스트로 보내기 전에 배열로 바꾸어서 보낸다. 
	public CalendarInfoModel[][] MapToMatrix(TreeMap <String, CalendarInfoModel> map)
	{
		int WEEK = map.size() / 7;
//		String YMD = "";
		CalendarInfoModel[][] calendarList = new CalendarInfoModel[WEEK][7];
		
//		// 첫 날을 기준으로 잡고 +1씩 cal.add 하면서 배열에 넣는다.
//		Calendar cal = Calendar.getInstance();
//		cal.set(PREVIOUS_YEAR, PREVIOUS_MONTH-1, PREVIOUS_DATE);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 주간 (줄)
//					for( int i = 0 ; i < WEEK; i++ )
//					{
//						// 요일 (칸)
//						for (int j = 0 ; j < 7 ; j++ )
//						{
//							calendarList[i][j] = entry.getValue();
//							
//			//				YMD = dateFormat.format(cal.getTime());
//							//calendarList[i][j] = 
//			//				cal.add(Calendar.DAY_OF_YEAR, 1);
//						}
//					
//					}
		int i = 0;
		int j = 0;
		// map을 1번부터 돌려가며 안에 넣기.
		for(Map.Entry<String, CalendarInfoModel> entry: map.entrySet())
		{
			calendarList[i][j] = entry.getValue();
			j++;
			
			if(j == 7){
				j=0;
				i++;
			}	
		}
		return calendarList;
	}
	
	// 해당 날짜(YMD)의 정보가 CalendarInfoModel에 담겨 calendarLoad메서드에 포함된다.
	private CalendarInfoModel setCalendarInfo(String _YMD, int _DAY){
		
		String[] tmp = _YMD.split("-");
		
		String _YEAR = tmp[0];
		String _MONTH = tmp[1];
		String _DATE = tmp[2];
		
		CalendarInfoModel calendarInfoModel = new CalendarInfoModel();
		calendarInfoModel.setYMD(_YMD);
		calendarInfoModel.setYEAR(_YEAR);
		calendarInfoModel.setMONTH(_MONTH);
		calendarInfoModel.setDATE(_DATE);
		calendarInfoModel.setDAY(_DAY);

		// 해당 월인지 체크
		if( Integer.parseInt(_MONTH) == STANDARD_MONTH ){calendarInfoModel.setTHIS_MONTH(true);}
		else{ calendarInfoModel.setTHIS_MONTH(false); }
		return calendarInfoModel;
	}
	
	public int getSTANDARD_YEAR() {
		return STANDARD_YEAR;
	}
	public void setSTANDARD_YEAR(int sTANDARD_YEAR) {
		STANDARD_YEAR = sTANDARD_YEAR;
	}
	public int getSTANDARD_MONTH() {
		return STANDARD_MONTH;
	}
	public void setSTANDARD_MONTH(int sTANDARD_MONTH) {
		STANDARD_MONTH = sTANDARD_MONTH;
	}

	public int getPREVIOUS_YEAR() {
		return PREVIOUS_YEAR;
	}

	public void setPREVIOUS_YEAR(int pREVIOUS_YEAR) {
		PREVIOUS_YEAR = pREVIOUS_YEAR;
	}

	public int getPREVIOUS_MONTH() {
		return PREVIOUS_MONTH;
	}

	public void setPREVIOUS_MONTH(int pREVIOUS_MONTH) {
		PREVIOUS_MONTH = pREVIOUS_MONTH;
	}

	public int getPREVIOUS_DATE() {
		return PREVIOUS_DATE;
	}

	public void setPREVIOUS_DATE(int pREVIOUS_DATE) {
		PREVIOUS_DATE = pREVIOUS_DATE;
	}

	public int getPREVIOUS_DAY() {
		return PREVIOUS_DAY;
	}

	public void setPREVIOUS_DAY(int pREVIOUS_DAY) {
		PREVIOUS_DAY = pREVIOUS_DAY;
	}

	public int getSTANDARD_DATE() {
		return STANDARD_DATE;
	}

	public void setSTANDARD_DATE(int sTANDARD_DATE) {
		STANDARD_DATE = sTANDARD_DATE;
	}

	public int getSTANDARD_DAY() {
		return STANDARD_DAY;
	}

	public void setSTANDARD_DAY(int sTANDARD_DAY) {
		STANDARD_DAY = sTANDARD_DAY;
	}

	public int getLAST_DATE() {
		return LAST_DATE;
	}

	public void setLAST_DATE(int lAST_DATE) {
		LAST_DATE = lAST_DATE;
	}

	public int getLAST_DAY() {
		return LAST_DAY;
	}

	public void setLAST_DAY(int lAST_DAY) {
		LAST_DAY = lAST_DAY;
	}

	public int getCLOSING_DATE() {
		return CLOSING_DATE;
	}

	public void setCLOSING_DATE(int cLOSING_DATE) {
		CLOSING_DATE = cLOSING_DATE;
	}

	public int getCLOSING_DAY() {
		return CLOSING_DAY;
	}

	public void setCLOSING_DAY(int cLOSING_DAY) {
		CLOSING_DAY = cLOSING_DAY;
	}

}