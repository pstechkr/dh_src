package user.model;

public class TicketInfoModel {

	private String schedule_seq; // 스케줄 시컨스 
	private String order_no; // 주문번호 
	private String user_seq; // 유저 시컨스
	private String gathering_seq; // 게더링 시컨스 
	private String user_name; // 예약자 이름 
	private String user_phoneno; // 예약자 폰번호 
	private String member; // 예약인원 
	private String ymd; // 진행 날짜
	private String name; // 상품 이름
	private String price; // 가격 ( member * menu price )
	
	// gathering 
	private String start_time; // 시작 시간
	private String end_time; // 끝나는 시간
	
	// store menu
	private String menu_time;
	private String schedule_hm;
	
	// 지도
	private String longitude;
	private String latitude;
	
	private String status; // 진행상태
	private String reg_cdd; // 신청일 일자
	private String reg_cdt; // 신청일 일시
	
	private String product; // 어떤 상품인지.
	private String product_name; // 상품 한글 이름.
	private String account_way; // 결제방법
	
	
	// 평가하기
	private String point;
	
	
	public String getSchedule_seq() {
		return schedule_seq;
	}
	public void setSchedule_seq(String schedule_seq) {
		this.schedule_seq = schedule_seq;
	}
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getGathering_seq() {
		return gathering_seq;
	}
	public void setGathering_seq(String gathering_seq) {
		this.gathering_seq = gathering_seq;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phoneno() {
		return user_phoneno;
	}
	public void setUser_phoneno(String user_phoneno) {
		this.user_phoneno = user_phoneno;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getMenu_time() {
		return menu_time;
	}
	public void setMenu_time(String menu_time) {
		this.menu_time = menu_time;
	}
	public String getSchedule_hm() {
		return schedule_hm;
	}
	public void setSchedule_hm(String schedule_hm) {
		this.schedule_hm = schedule_hm;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReg_cdd() {
		return reg_cdd;
	}
	public void setReg_cdd(String reg_cdd) {
		this.reg_cdd = reg_cdd;
	}
	public String getReg_cdt() {
		return reg_cdt;
	}
	public void setReg_cdt(String reg_cdt) {
		this.reg_cdt = reg_cdt;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getAccount_way() {
		return account_way;
	}
	public void setAccount_way(String account_way) {
		this.account_way = account_way;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
	
}
