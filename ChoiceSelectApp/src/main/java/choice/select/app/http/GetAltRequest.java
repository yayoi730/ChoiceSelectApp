package choice.select.app.http;

public class GetAltRequest {
	String aID;
	
	// Must include getters, setters, empty constructor to work with AWS

	public GetAltRequest() {
	}
	
	public GetAltRequest(String id) {
		this.aID = id;
	}
	
	public String getAID() { return aID; }
	public void setAID(String id) { this.aID = id; }
	
	public String toString() {
		return "GetAlternative(" + getAID() + ")";
	}
}
