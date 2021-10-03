package DB.bean;

import java.io.Serializable;

public class PatientAccount implements Serializable{
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "PatientAccount [email=" + email + ", pw=" + password + "]";
	}
	
}
