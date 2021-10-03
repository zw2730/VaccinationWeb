package DB.bean;

import java.io.Serializable;

public class Provider implements Serializable{
	private int pid;
	private String name;
	private String phone;
	private String address;
	private String providerType;
	public String getProviderType() {
		return providerType;
	}
	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}
	private String latitude;
	private String longitude;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Provider [pid=" + pid + ", name=" + name + ", phone=" + phone + ", address=" + address
				+ ", providerType=" + providerType + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}