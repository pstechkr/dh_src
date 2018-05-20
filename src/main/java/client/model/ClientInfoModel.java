package client.model;

import org.springframework.web.multipart.MultipartFile;

public class ClientInfoModel {

	/* input
	 * client_seq = 클라이언트 번호
	 * client_email = 이메일 주소 (아이디)
	 * client_password = 비밀번호
	 * client_name = 이름
	 * client_companyname = 사업자등록증 상의 회사명
	 * client_contactnumber = 클라이언트 연락 번호
	 * client_license = 사업자등록번호
	 * client_license_pic = 사업자등록번호 사진 경로
	 * client_category1 = 업태
	 * client_category2 = 종목
	 * client_banking = 은행
	 * client_contactemail = 클라이언트 연락 이메일
	 */
	
	private String client_seq;
	private String client_email;
	private String client_password;
	private String client_name;
	private String client_companyname;
	private String client_contactnumber;
	private String client_license;
	private String client_license_pic;
	private String client_category1;
	private String client_category2;
	private String client_banking;
	private String client_contactemail;
	
	// file
	private MultipartFile client_license_pic_file;
	
	// getter,setter
	public String getClient_seq() {
		return client_seq;
	}
	public void setClient_seq(String client_seq) {
		this.client_seq = client_seq;
	}
	public String getClient_email() {
		return client_email;
	}
	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}
	public String getClient_password() {
		return client_password;
	}
	public void setClient_password(String client_password) {
		this.client_password = client_password;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_companyname() {
		return client_companyname;
	}
	public void setClient_companyname(String client_companyname) {
		this.client_companyname = client_companyname;
	}
	public String getClient_contactnumber() {
		return client_contactnumber;
	}
	public void setClient_contactnumber(String client_contactnumber) {
		this.client_contactnumber = client_contactnumber;
	}
	public String getClient_license() {
		return client_license;
	}
	public void setClient_license(String client_license) {
		this.client_license = client_license;
	}
	public String getClient_license_pic() {
		return client_license_pic;
	}
	public void setClient_license_pic(String client_license_pic) {
		this.client_license_pic = client_license_pic;
	}
	public String getClient_category1() {
		return client_category1;
	}
	public void setClient_category1(String client_category1) {
		this.client_category1 = client_category1;
	}
	public String getClient_category2() {
		return client_category2;
	}
	public void setClient_category2(String client_category2) {
		this.client_category2 = client_category2;
	}
	public String getClient_banking() {
		return client_banking;
	}
	public void setClient_banking(String client_banking) {
		this.client_banking = client_banking;
	}
	public String getClient_contactemail() {
		return client_contactemail;
	}
	public void setClient_contactemail(String client_contactemail) {
		this.client_contactemail = client_contactemail;
	}
	public MultipartFile getClient_license_pic_file() {
		return client_license_pic_file;
	}
	public void setClient_license_pic_file(MultipartFile client_license_pic_file) {
		this.client_license_pic_file = client_license_pic_file;
	}
}
