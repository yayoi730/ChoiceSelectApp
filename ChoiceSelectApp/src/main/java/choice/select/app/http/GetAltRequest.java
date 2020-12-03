package choice.select.app.http;

public class GetAltRequest {
	int altNumber;

	String cID;
	
	public GetAltRequest() {}
	public GetAltRequest(int altNumber, String cid) {this.altNumber = altNumber; this.cID = cid;}
	
	public int getAltNumber() {return this.altNumber;}
	public void setAltNumber(int altNumber) {this.altNumber = altNumber;}
	public String getcID() {return this.cID;}
	public void setcID(String id) {this.cID = id;}

}
