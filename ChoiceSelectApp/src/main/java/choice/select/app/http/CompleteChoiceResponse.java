package choice.select.app.http;

public class CompleteChoiceResponse {
	public final int httpCode;
	public final String error;
	
	// 200 means success
		public CompleteChoiceResponse () {
			this.httpCode = 200;
			this.error = "";
		}		
		
		public CompleteChoiceResponse (int code, String errormsg) {
			this.httpCode = code;
			this.error = errormsg;
		}

		public String toString() {
			if(httpCode == 200)
			{
				return "CompleteResponse(200)";
			}
			else {return "ErrorCompleteResponse(" + error + ")";}
		}
	
}
