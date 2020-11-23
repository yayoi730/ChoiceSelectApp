package choice.select.app.http;

public class CreateChoiceResponse {
	public final String response;
	public final int httpCode;
	public final String error;
	
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
