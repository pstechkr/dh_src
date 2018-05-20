package client.model;

public class StoreInfoModel {
	
	/* input
	 * store_seq = 매장 번호
	 * client_seq = 클라이언트 번호
	 * store_name = 매장 이름
	 * store_longitude = 매장 주소지 (위도)
	 * store_latitude = 매장 주소지 (경도)
	 * store_localtitle = 매장 주소지 (전체 주소)
	 * store_localname1 = 매장 주소지 (광역시 이상, 도)
	 * store_localname2 = 매장 주소지 (시/군/구)
	 * store_localname2 = 매장 주소지 (동)
	 * store_contactnumber = 매장 연락처
	 * store_openinghours1 = 매장 개점시간
	 * store_openinghours2 = 매장 폐점시간
	 * store_breaktime1 = 매장 브레이크타임 시작
	 * store_breaktime2 = 매장 브레이크타임 마침
	 * store_dayoff = 매장 휴무일
	 * store_parkinginfo = 주차정보
	 * store_intromessage = 소개말
	 * store_point = 매장 인기도 정보
	 * reservation_yn = 예약기능 사용 판별 (0: 사용안함 1: 사용함)
	 * reservation_openinghours1 = 예약 시작 시간
	 * reservation_openinghours2 = 예약 마감 시간
	 * reservation_breaktime_yn = 브레이크타임에도 예약을 받을까? (0: 안받음 1: 받음)
	 * reservation_max = 시간당 최대 예약인원 
	 */
	
	
	/*
	 * modifyYN = 수정 성공 여부
	 * 
	 */
	
	
	// db
	private String store_seq;
	private String client_seq;
	private String store_name;
	private String store_longitude;
	private String store_latitude;
	private String store_localtitle;
	private String store_localname1;
	private String store_localname2;
	private String store_localname3;
	private String store_localname4;
	private String store_contactnumber;
	private String store_openinghours1;
	private String store_openinghours2;
	private String store_breaktime1;
	private String store_breaktime2;
	private String store_dayoff;
	private String store_parkinginfo;
	private String store_intromessage;
	private String store_point;
	private String reservation_yn;
	private String reservation_openinghours1;
	private String reservation_openinghours2;
	private String reservation_breaktime_yn;
	private String reservation_max;
	
	private String modifyYN;
	
	// getter, setter
	public String getStore_seq() {
		return store_seq;
	}
	public void setStore_seq(String store_seq) {
		this.store_seq = store_seq;
	}
	public String getClient_seq() {
		return client_seq;
	}
	public void setClient_seq(String client_seq) {
		this.client_seq = client_seq;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_longitude() {
		return store_longitude;
	}
	public void setStore_longitude(String store_longitude) {
		this.store_longitude = store_longitude;
	}
	public String getStore_latitude() {
		return store_latitude;
	}
	public void setStore_latitude(String store_latitude) {
		this.store_latitude = store_latitude;
	}
	public String getStore_localname1() {
		return store_localname1;
	}
	public void setStore_localname1(String store_localname1) {
		this.store_localname1 = store_localname1;
	}
	public String getStore_localname2() {
		return store_localname2;
	}
	public void setStore_localname2(String store_localname2) {
		this.store_localname2 = store_localname2;
	}
	public String getStore_contactnumber() {
		return store_contactnumber;
	}
	public void setStore_contactnumber(String store_contactnumber) {
		this.store_contactnumber = store_contactnumber;
	}
	public String getStore_openinghours1() {
		return store_openinghours1;
	}
	public void setStore_openinghours1(String store_openinghours1) {
		this.store_openinghours1 = store_openinghours1;
	}
	public String getStore_openinghours2() {
		return store_openinghours2;
	}
	public void setStore_openinghours2(String store_openinghours2) {
		this.store_openinghours2 = store_openinghours2;
	}
	public String getStore_breaktime1() {
		return store_breaktime1;
	}
	public void setStore_breaktime1(String store_breaktime1) {
		this.store_breaktime1 = store_breaktime1;
	}
	public String getStore_breaktime2() {
		return store_breaktime2;
	}
	public void setStore_breaktime2(String store_breaktime2) {
		this.store_breaktime2 = store_breaktime2;
	}
	public String getStore_dayoff() {
		return store_dayoff;
	}
	public void setStore_dayoff(String store_dayoff) {
		this.store_dayoff = store_dayoff;
	}
	public String getStore_parkinginfo() {
		return store_parkinginfo;
	}
	public void setStore_parkinginfo(String store_parkinginfo) {
		this.store_parkinginfo = store_parkinginfo;
	}
	public String getStore_point() {
		return store_point;
	}
	public void setStore_point(String store_point) {
		this.store_point = store_point;
	}
	public String getReservation_yn() {
		return reservation_yn;
	}
	public void setReservation_yn(String reservation_yn) {
		this.reservation_yn = reservation_yn;
	}
	public String getReservation_openinghours1() {
		return reservation_openinghours1;
	}
	public void setReservation_openinghours1(String reservation_openinghours1) {
		this.reservation_openinghours1 = reservation_openinghours1;
	}
	public String getReservation_openinghours2() {
		return reservation_openinghours2;
	}
	public void setReservation_openinghours2(String reservation_openinghours2) {
		this.reservation_openinghours2 = reservation_openinghours2;
	}
	public String getReservation_breaktime_yn() {
		return reservation_breaktime_yn;
	}
	public void setReservation_breaktime_yn(String reservation_breaktime_yn) {
		this.reservation_breaktime_yn = reservation_breaktime_yn;
	}
	public String getReservation_max() {
		return reservation_max;
	}
	public void setReservation_max(String reservation_max) {
		this.reservation_max = reservation_max;
	}
	public String getStore_localtitle() {
		return store_localtitle;
	}
	public void setStore_localtitle(String store_localtitle) {
		this.store_localtitle = store_localtitle;
	}
	public String getStore_localname3() {
		return store_localname3;
	}
	public void setStore_localname3(String store_localname3) {
		this.store_localname3 = store_localname3;
	}
	public String getModifyYN() {
		return modifyYN;
	}
	public void setModifyYN(String modifyYN) {
		this.modifyYN = modifyYN;
	}
	public String getStore_localname4() {
		return store_localname4;
	}
	public void setStore_localname4(String store_localname4) {
		this.store_localname4 = store_localname4;
	}
	public String getStore_intromessage() {
		return store_intromessage;
	}
	public void setStore_intromessage(String store_intromessage) {
		this.store_intromessage = store_intromessage;
	}
}
