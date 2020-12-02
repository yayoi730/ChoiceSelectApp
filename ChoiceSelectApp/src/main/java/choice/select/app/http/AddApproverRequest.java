package choice.select.app.http;

public class AddApproverRequest {
	
	String name;
	int altNum;		//unsure
	String cid;		//cid = tid
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public String toString() {
		return "AddApproverRequest(" + name + ", cid: " + cid + ", alt:" + altNum + ")";
	}
	
	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }
	
	public int getAltNum() { return this.altNum; }
	public void setAltNum(int altNum) { this.altNum = altNum; }
	
	public String getCid() { return this.cid; }
	public void setCid(String cid) { this.cid = cid; }
	
	public AddApproverRequest() {}
	
	public AddApproverRequest(String name, int altNum, String cid) {
		this.name = name;
		this.altNum = altNum;
		this.cid = cid;
	}

}
