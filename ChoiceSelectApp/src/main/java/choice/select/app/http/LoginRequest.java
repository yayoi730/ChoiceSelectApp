package choice.select.app.http;

public class LoginRequest {
	String name;
	String password;
	public String cid;
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public String getName() {return name;}
	public void setname(String newName) { this.name = newNAme; }
	
	public String getPassword() {return password;}
	public void setPassword(String newPass) { this.password = newPass; }
	
	public String getCid() {return cid;}
	public void setCid(String newCid) { cid = newCid; }

	public LoginRequest() {
	}

	public LoginRequest(String name, String cid) {
		this.name = name;
		this.password = "";	
		this.cid = cid;
	}
	
	public LoginRequest(String name, String password, String cid) {
		this.name = name;
		this.password = password;
		this.cid = cid;
	}
	
	public String toString() {
		if (password == null || password == "") {
			return "Login(" + name + ", " + cid + ")";
		}
		return "Login(" + name + ", "  + password + ", "+ cid + ")";
	}
	
}

