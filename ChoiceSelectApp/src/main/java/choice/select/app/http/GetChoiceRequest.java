package choice.select.app.http;

public class GetChoiceRequest {
	String id;

	public GetChoiceRequest(){}
	
	public GetChoiceRequest(String id){
		this.id = id;
	}
	
	public void setID(String id) { this.id = id;}
	public String getID() {	return this.id;}
}
