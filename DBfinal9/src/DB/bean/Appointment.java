package DB.bean;

import java.io.Serializable;

public class Appointment implements Serializable{
	private int aid;
	private String datetime;
	private int pid;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "appointment [aid=" + aid + ", datetime=" + datetime + ", pid=" + pid + "]";
	}
	
}
