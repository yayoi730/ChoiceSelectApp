package choice.select.app.http;

import rando.randomness.app.demo.model.Member;
import rando.randomness.app.demo.model.Team;

public class LoginResponse {
	public final Member member;
	public final Team team;
	public final int httpCode;
	public final String error;
	
	public LoginResponse (int code, String errormsg) {
		this.member = null;
		this.team = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public LoginResponse (Member m, Team t) {
		this.member = m;
		this.team = t;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "LoginResponse(" + member + ", " + team +")";
		}
		else {return "LoginError(" + error + ")";}
	}
}
