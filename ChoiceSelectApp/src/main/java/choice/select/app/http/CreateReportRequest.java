package choice.select.app.http;

public class CreateReportRequest {
	
	boolean wantReports;
	
	public String toString() {
		return String.valueOf(wantReports);
	}
	
	public boolean getWantReports() {return this.wantReports;}
	public void setWantReports(boolean b) {this.wantReports = b;}
	
	// Must include getters, setters, empty constructor to work with AWS
	
	public CreateReportRequest() {}
	
	public CreateReportRequest(boolean b) {
		this.wantReports = b;
	}
}
