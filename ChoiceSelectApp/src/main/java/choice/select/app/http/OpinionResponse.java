package choice.select.app.http;

import rando.randomness.app.demo.model.Alternative;

public class OpinionResponse {
	public Alternative alt;
	public final int httpCode;
	public final String error;
	
	public OpinionResponse(Alternative alt) {
		this.alt = alt;
		this.httpCode = 200;
		this.error = "";
	}
	
	public OpinionResponse(int code, String err) {
		this.httpCode = code;
		this.error = err;
	}

}
