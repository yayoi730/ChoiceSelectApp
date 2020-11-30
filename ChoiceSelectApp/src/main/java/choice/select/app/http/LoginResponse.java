package choice.select.app.http;

import rando.randomness.app.demo.model.Member;

public class LoginResponse {
	public final Member member;
	public final int httpCode;
	public final String error;
	
	public LoginResponse (int code, String errormsg) {
		this.member = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public LoginResponse (Member m) {
		this.member = m;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "LoginResponse(" + member + ")";
		}
		else {return "LoginError(" + error + ")";}
	}
}
