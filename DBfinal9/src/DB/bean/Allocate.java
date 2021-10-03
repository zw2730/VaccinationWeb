package DB.bean;

import java.io.Serializable;

public class Allocate implements Serializable{
	private String ssn;
	private int aid;
	private String expireTime;
	private boolean accept;
	private boolean showUp;
	private boolean cancel;
	
	private Patient patient;
	private Appointment appointment;
	private Provider provider;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	public boolean isShowUp() {
		return showUp;
	}
	public void setShowUp(boolean showUp) {
		this.showUp = showUp;
	}
	public String getSsn() {
		return ssn;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	@Override
	public String toString() {
		return "Allocate [ssn=" + ssn + ", aid=" + aid + ", expireTime=" + expireTime + ", accept=" + accept
				+ ", showUp=" + showUp + ", cancel=" + cancel + ", patient=" + patient + ", appointment=" + appointment
				+ ", provider=" + provider + "]";
	}
	
}
