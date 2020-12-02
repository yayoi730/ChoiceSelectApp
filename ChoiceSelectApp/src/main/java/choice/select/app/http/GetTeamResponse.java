package choice.select.app.http;

import rando.randomness.app.demo.model.Team;

public class GetTeamResponse {
	public final Team team;
	public final int httpCode;
	public final String error;
	
	// OK 200
	public GetTeamResponse(Team t) {
		this.team = t;
		this.httpCode = 200;
		this.error = "";
	}
	
	//error
	public GetTeamResponse(int code, String error) {
		this.team = null;
		this.httpCode = code;
		this.error = error;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "GetTeamResponse(" + team.toString() + ")";
		}
		else return "GetTeamError("+ this.httpCode + ", " + this.error + ")";
	}

}
