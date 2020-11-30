package choice.select.app.http;

public class CreateMemberRequest {
	String name;
	String password;
	public String tid;
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public String getName() {return this.name;}
	public void setName(String newName) { name = newName; }
	
	public String getPassword() {return this.password;}
	public void setPassword(String newPass) { password = newPass; }
	
	public String getTid() {return this.tID;}
	public void setTid(String newTid) { tid = newTid; }
	
	public CreateMemberRequest() {
	}
	
	public CreateMemberRequest(String name, String cid) {
		this.name = name;
		this.cid = cid;
	}
	
	public CreateMemberRequest(String name, String password, String cid) {
		this.name = name;
		this.password = password;
		this.cid = cid;
	}
	
	public String toString() {
		if (password == null || password == "") {
			return "CreateMember(" + name + ", " + cid + ")";
		}
		return "CreateMember(" + name + ", "  + password + ", "+ cid + ")";
	}
	
}
