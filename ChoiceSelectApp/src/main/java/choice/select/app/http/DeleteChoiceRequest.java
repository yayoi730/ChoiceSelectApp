package choice.select.app.http;

public class DeleteChoiceRequest {
	
	public String tID;
	
	public DeleteChoiceRequest(){}
	
	public DeleteChoiceRequest(String tID){this.tID = tID;}
	
	public void settID(String tID) {
		this.tID = tID;
	}
	
	public String gettID() {
		return this.tID;
	}
	
	public String toString() {
		return "Delete " + this.tID;
	}
	
}
