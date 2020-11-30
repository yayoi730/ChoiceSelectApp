package choice.select.app.http;

public class CreateChoiceResponse {
	public String response;
	public int httpCode;
	public String error;
	
	//For failure
	public CreateChoiceResponse (String s, int code, String errormsg) {
		this.response = s;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public CreateChoiceResponse (String s) {
		this.response = s;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
