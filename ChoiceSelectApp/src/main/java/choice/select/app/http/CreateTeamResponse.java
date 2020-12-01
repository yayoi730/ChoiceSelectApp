package choice.select.app.http;

import rando.randomness.app.demo.model.Team;

public class CreateTeamResponse {
	public final Team team;
	public final int httpCode;
	public final String error;
	
	public CreateTeamResponse (int code, String errormsg) {
		this.team = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public CreateTeamResponse (Team t) {
		this.team = t;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "TeamResponse(" + team.toString() + ")";
		}
		else return "TeamResponseError("+ this.httpCode + ", " + this.error + ")";
	}
}
