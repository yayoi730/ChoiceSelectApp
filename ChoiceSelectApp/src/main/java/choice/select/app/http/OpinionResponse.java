package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Report;

public class OpinionResponse {
	public final int httpCode;
	public final String error;
	
	public OpinionResponse(ArrayList<Report> r) {
		this.httpCode = 200;
		this.error = "";
	}
	
	public OpinionResponse(int code, String err) {
		this.httpCode = code;
		this.error = err;
	}

}
