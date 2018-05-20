package client.model;

public class TimeTableModel {

	private String time;
	private String ready;
	private String doing;
	private int readyInt;
	private int doingInt;
	private int max;
	private boolean reservation; 
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReady() {
		return ready;
	}
	public void setReady(String ready) {
		this.ready = ready;
	}
	public String getDoing() {
		return doing;
	}
	public void setDoing(String doing) {
		this.doing = doing;
	}
	public int getReadyInt() {
		return readyInt;
	}
	public void setReadyInt(int readyInt) {
		this.readyInt = readyInt;
	}
	public int getDoingInt() {
		return doingInt;
	}
	public void setDoingInt(int doingInt) {
		this.doingInt = doingInt;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public boolean isReservation() {
		return reservation;
	}
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}
}
