package client.model;

public class CalendarInfoModel {

	// 날짜 관련 기본정보 
	private String YMD;
	private String YEAR;
	private String MONTH;
	private String DATE;
	private int DAY;
	
	
	// jsp에서 이번달만 클릭 가능하도록 하는 키
	private boolean THIS_MONTH;
	
	// 커스텀 예약 관리에서의 스위치
	private boolean RESERVATION_SWITCH;
	
	public String getYMD() {
		return YMD;
	}
	public void setYMD(String yMD) {
		YMD = yMD;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public int getDAY() {
		return DAY;
	}
	public void setDAY(int dAY) {
		DAY = dAY;
	}
	public boolean isTHIS_MONTH() {
		return THIS_MONTH;
	}
	public void setTHIS_MONTH(boolean tHIS_MONTH) {
		THIS_MONTH = tHIS_MONTH;
	}
	public boolean isRESERVATION_SWITCH() {
		return RESERVATION_SWITCH;
	}
	public void setRESERVATION_SWITCH(boolean rESERVATION_SWITCH) {
		RESERVATION_SWITCH = rESERVATION_SWITCH;
	}
}
