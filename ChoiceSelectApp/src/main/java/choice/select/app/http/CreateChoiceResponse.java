package choice.select.app.http;

import rando.randomness.app.demo.model.Choice;

public class CreateChoiceResponse {

	public final Choice choice;
	public final int httpCode;
	public final String error;

	public CreateChoiceResponse (int code, String errormsg) {
		this.choice = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public CreateChoiceResponse (Choice c) {
		this.choice = c;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "Result(" + choice + ")";
		}
		else {return "ErrorResult(" + httpCode + ", " + error +")";}
	}
}
