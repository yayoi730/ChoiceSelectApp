package choice.select.app.http;

public class CreateMemberRequest {
	String name;
	String password;
	public String mID;
	public String tID;
	
	public CreateMemberRequest() {
	}
	public CreateMemberRequest(String name, String mID, String tID) {
		this.name = name;
		this.password = "";
		this.mID = mID;
		this.tID = tID;
	}
	public CreateMemberRequest(String name, String password, String mID, String tID) {
		this.name = name;
		this.password = password;
		this.mID = mID;
		this.tID = tID;
	}
	public String getName() {return this.name;}
	public String getPassword() {return this.password;}
	public String getMID() {return this.mID;}
	public String getTID() {return this.tID;}
}
