package choice.select.app.http;

public class GetAltRequest {
	String aID;
	public GetAltRequest() {}
	public GetAltRequest(String id) {this.aID = id;}
	
	public String getAID() {return this.aID;}
	public void getAID(String id) {this.aID = id;}
}
