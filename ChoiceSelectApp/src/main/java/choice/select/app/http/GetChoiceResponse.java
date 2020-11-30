package choice.select.app.http;

import rando.randomness.app.demo.model.Choice;

public class GetChoiceResponse {
	public final Choice choice;
	public final int httpCode;
	public final String error;
	
	public GetChoiceResponse (int code, String errormsg) {
		this.choice = null;
		this.httpCode = code;
		this.error = errormsg;
	}
	
	// 200 means success
	public GetChoiceResponse (Choice loadedChoice) {
		this.choice = loadedChoice;
		this.httpCode = 200;
		this.error = "";
	}
	
	public String toString() {
		if(httpCode == 200){
			return "GetChoice(" + choice.toString() + ")";
		}
		else {return "GetChoiceErr(" + error + ")";}
	}
}
