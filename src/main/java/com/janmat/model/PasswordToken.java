package com.janmat.model;


public class PasswordToken {
	
	private String passToken;
	private String email;
	private String  timestamp ;
	private String timeout;
	
	public String getPassToken() {
		return passToken;
	}
	public void setPassToken(String passToken) {
		this.passToken = passToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimesout(String timeout) {
		this.timeout = timeout;
	}
	
	
	@Override
    public String toString() {
    return " PassWordToken [passToken=" + passToken + ", email=" + email
    + ", timestamp=" + timestamp + ", timeout=" + timeout + "]";
	}
	

}
