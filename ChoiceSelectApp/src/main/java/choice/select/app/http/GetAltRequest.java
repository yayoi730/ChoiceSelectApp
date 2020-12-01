package choice.select.app.http;

public class GetAltRequest {
	String aID;

	String cID;
	
	public GetAltRequest() {}
	public GetAltRequest(String aid, String cid) {this.aID = aid; this.cID = cid;}
	
	public String getAID() {return this.aID;}
	public void setAID(String id) {this.aID = id;}
	public String getCID() {return this.cID;}
	public void setCID(String id) {this.cID = id;}

}
