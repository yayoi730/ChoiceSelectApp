package choice.select.app.http;

public class GetTeamRequest {
	
	String tid;
	
	public String toString() {
		return "GetTeamRequest(team: " + tid + ")";
	}
	
	public String getTid() { return this.tid; }
	public void setTid(String tid) { this.tid = tid; }
	
	public GetTeamRequest() {
	}
	
	public GetTeamRequest(String tid) {
		this.tid = tid;
	}

}
