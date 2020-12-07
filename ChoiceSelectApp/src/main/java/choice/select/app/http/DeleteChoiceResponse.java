package choice.select.app.http;

public class DeleteChoiceResponse {
	public final int httpCode;
	public final String error;
	
	// 200 means success
		public DeleteChoiceResponse () {
			this.httpCode = 200;
			this.error = "";
		}		
		
		public DeleteChoiceResponse (int code, String errormsg) {
			this.httpCode = code;
			this.error = errormsg;
		}

		public String toString() {
			if(httpCode == 200)
			{
				return "DeleteResponse(200)";
			}
			else {return "ErrorDeleteResponse(" + error + ")";}
		}

}
