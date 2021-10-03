package DB.bean;

import java.io.Serializable;

public class Patient implements Serializable{
	private String ssn;
	private String name;
	private String birthdate;
	private String address;
	private String phone;
	private Double maxDist;
	private String email;
	private int gid;
	private Double latitude;
	private Double longitude;
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getMaxDist() {
		return maxDist;
	}
	public void setMaxDist(Double maxDist) {
		this.maxDist = maxDist;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Patient [ssn=" + ssn + ", name=" + name + ", birthdate=" + birthdate + ", address=" + address
				+ ", phone=" + phone + ", maxDist=" + maxDist + ", email=" + email + ", gid=" + gid + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
}
