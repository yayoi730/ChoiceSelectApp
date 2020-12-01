package choice.select.app.http;

import rando.randomness.app.demo.model.Alternative;

public class GetAltResponse {
	public final Alternative alt;
	public final int httpCode;
	public final String error;
	
	public GetAltResponse (int code, String errormsg) {
		this.alt = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public GetAltResponse (Alternative s) {
		this.alt = s;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		return "Response(" + alt + ")";
	}
}
