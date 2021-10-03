package DB.bean;

import java.io.Serializable;

public class PatientPreferredTime implements Serializable{
	private String ssn;
	private int day;
	private String slotStarttime;
	private String slotEndtime;
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getSlotStarttime() {
		return slotStarttime;
	}
	public void setSlotStarttime(String slotStarttime) {
		this.slotStarttime = slotStarttime;
	}
	public String getSlotEndtime() {
		return slotEndtime;
	}
	public void setSlotEndtime(String slotEndtime) {
		this.slotEndtime = slotEndtime;
	}
	@Override
	public String toString() {
		return "PatientPreferredTime [ssn=" + ssn + ", day=" + day + ", slotStarttime=" + slotStarttime
				+ ", slotEndtime=" + slotEndtime + "]";
	}
	
}
