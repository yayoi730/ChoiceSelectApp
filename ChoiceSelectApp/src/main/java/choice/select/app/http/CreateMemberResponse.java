package choice.select.app.http;

import rando.randomness.app.demo.model.Member;

public class CreateMemberResponse {
	public final Member member;
	public final int httpCode;
	public final String error;
	
	public CreateMemberResponse (int code, String errormsg) {
		this.member = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public CreateMemberResponse (Member nM) {
		this.member = nM;
		this.httpCode = 200;
		this.error = "";
	}
	

	public String toString() {
		if(httpCode == 200)
		{
			return "MemberResponse(" + member.getName() + ")";
		}
		else {return "ErrorMemberResponse(" + error + ")";}
	}
}
