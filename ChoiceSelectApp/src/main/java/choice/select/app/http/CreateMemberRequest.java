package choice.select.app.http;

public class CreateMemberRequest {
	String name;
	String password;
	public String tID;
	
	public CreateMemberRequest() {
	}
	public CreateMemberRequest(String name, String tID) {
		this.name = name;
		this.password = "";	
		this.tID = tID;
	}
	public CreateMemberRequest(String name, String password, String tID) {
		this.name = name;
		this.password = password;
		this.tID = tID;
	}
	public String getName() {return this.name;}
	public String getPassword() {return this.password;}
	public String getTID() {return this.tID;}
}
