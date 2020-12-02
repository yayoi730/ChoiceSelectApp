package choice.select.app.http;

import rando.randomness.app.demo.model.Team;

public class AddApproverResponse {
	public final Team team;			//return team to have access to all attributes
	public final int httpCode;
	public final String error;
	
	public AddApproverResponse (int code, String errormsg) {
		this.team = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public AddApproverResponse(Team team) {		//unsure of object
		this.team = team;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "AddApproverResponse(" + team.toString() + ")";
		}
		else return "AddApproverResponseError("+ this.httpCode + ", " + this.error + ")";
	}
}
