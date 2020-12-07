package choice.select.app.http;

import rando.randomness.app.demo.model.Alternative;

public class CreateFeedbackResponse {
	public final Alternative alt;
	public final int httpCode;
	public final String error;
	
	// 200 means success
		public CreateFeedbackResponse (Alternative nM) {
			this.alt = nM;
			this.httpCode = 200;
			this.error = "";
		}		
		
		public CreateFeedbackResponse (int code, String errormsg) {
			this.alt = null;
			this.httpCode = code;
			this.error = errormsg;
		}

		public String toString() {
			if(httpCode == 200)
			{
				return "FeedbackResponse(" + alt.getFeebackList() + ")";
			}
			else {return "ErrorFeedbackResponse(" + error + ")";}
		}

}
