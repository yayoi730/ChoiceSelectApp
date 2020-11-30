package choice.select.app.http;

public class GetChoiceRequest {
	String cid;
	
	// Must include getters, setters, empty constructor to work with AWS

	public GetChoiceRequest(){
	}
	
	public GetChoiceRequest(String id){
		this.cid = id;
	}
	
	public String getID() {	return cid;}
	public void setID(String id) { this.cid = id;}
	
	public String toString() {
		return "GetChoice(" + cid + ")";
	}
	
}
