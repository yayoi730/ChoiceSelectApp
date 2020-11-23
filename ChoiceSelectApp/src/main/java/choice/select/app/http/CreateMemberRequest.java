package choice.select.app.http;

public class CreateMemberRequest {
	String name;
	String password;
	
	public CreateMemberRequest() {
	}
	public CreateMemberRequest(String name) {
		this.name = name;
	}
	public CreateMemberRequest(String name, String password) {
		this.name = name;
		this.password = password;
	}
	public String getName() {return this.name;}
	public String getPassword() {return this.password;}
}
