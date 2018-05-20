package user.model;

public class UserInfoModel {

	private String user_seq;
	private String user_email;
	private String user_password;
	private String user_name;
	private String user_gender;
	private String user_birthday;
	private String user_phoneno;
	private String delete_yn;
	private String facebook_id;
	private String reg_cdd;
	private String recent_login;
	private String recent_cdd;
	private String recent_cdt;
	
	private boolean success;
	
	public String getUser_seq() {
		return user_seq;
	}
	public void setUser_seq(String user_seq) {
		this.user_seq = user_seq;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_phoneno() {
		return user_phoneno;
	}
	public void setUser_phoneno(String user_phoneno) {
		this.user_phoneno = user_phoneno;
	}
	public String getDelete_yn() {
		return delete_yn;
	}
	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getReg_cdd() {
		return reg_cdd;
	}
	public void setReg_cdd(String reg_cdd) {
		this.reg_cdd = reg_cdd;
	}
	public String getRecent_login() {
		return recent_login;
	}
	public void setRecent_login(String recent_login) {
		this.recent_login = recent_login;
	}
	public String getRecent_cdd() {
		return recent_cdd;
	}
	public void setRecent_cdd(String recent_cdd) {
		this.recent_cdd = recent_cdd;
	}
	public String getRecent_cdt() {
		return recent_cdt;
	}
	public void setRecent_cdt(String recent_cdt) {
		this.recent_cdt = recent_cdt;
	} 
}
