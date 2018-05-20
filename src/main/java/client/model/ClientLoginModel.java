package client.model;

public class ClientLoginModel {

	//input
	private String client_email;
	private String client_password;
	
	//output
	private String client_name;
	private String client_seq;
	private boolean success;
	private String store_seq;

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
	public String getClient_seq() {
		return client_seq;
	}
	public void setClient_seq(String client_seq) {
		this.client_seq = client_seq;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getStore_seq() {
		return store_seq;
	}
	public void setStore_seq(String store_seq) {
		this.store_seq = store_seq;
	}
}
