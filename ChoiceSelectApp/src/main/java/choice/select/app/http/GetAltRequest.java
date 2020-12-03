package choice.select.app.http;

public class GetAltRequest {
	String aID;

	String cID;
	
	public GetAltRequest() {}
	public GetAltRequest(String aid, String cid) {this.aID = aid; this.cID = cid;}
	
	public String getaID() {return this.aID;}
	public void setaID(String id) {this.aID = id;}
	public String getcID() {return this.cID;}
	public void setcID(String id) {this.cID = id;}
	
	

}
