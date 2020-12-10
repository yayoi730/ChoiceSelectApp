package choice.select.app.http;


public class CreateFeedbackRequest {
	String aID;
	String cID;
	String description;
	String creator;
		
	public CreateFeedbackRequest() {}
		
	public CreateFeedbackRequest(String timestamp, String description, String creator) {
		
		this.description = description;
		this.creator = creator;
	}
		
	public CreateFeedbackRequest(String aID, String timestamp, String description, String creator) {
		this.aID = aID;
		this.description = description;
		this.creator = creator;
	}
		

	public String getDescription() {return this.description;}
	public String getCreator() {return this.creator;}
	public String getaID() {return this.aID;}
	public void setaID(String aID) {this.aID = aID;}
	public String getcID() {return this.cID;}
	public void setcID(String cID) {this.cID = cID;}
	public void setDescription(String desc) { this.description = desc;}
	public void setCreator(String c) { this.creator = c;}
		
	public String toString() {	
			return "("+ this.aID + ", " + this.creator + ", " + this.description + ")";
	}

}

