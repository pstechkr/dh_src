package service.model;

public class CouponInfoModel {

	private String coupon_seq;
	private String coupon_id;
	private String user_seq;
	private String coupon_name;
	private String coupon_limit;
	private String coupon_price;
	private String coupon_div;
	private String use_yn;
	private String use_registerdt;
	private String reg_cdd;
	private String reg_cdt;
	private String total_price;
	
	public String getCoupon_seq() {
		return coupon_seq;
	}
	public void setCoupon_seq(String coupon_seq) {
		this.coupon_seq = coupon_seq;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_limit() {
		return coupon_limit;
	}
	public void setCoupon_limit(String coupon_limit) {
		this.coupon_limit = coupon_limit;
	}
	public String getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(String coupon_price) {
		this.coupon_price = coupon_price;
	}
	public String getCoupon_div() {
		return coupon_div;
	}
	public void setCoupon_div(String coupon_div) {
		this.coupon_div = coupon_div;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUse_registerdt() {
		return use_registerdt;
	}
	public void setUse_registerdt(String use_registerdt) {
		this.use_registerdt = use_registerdt;
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
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
}
