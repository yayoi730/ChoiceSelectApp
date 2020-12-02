package choice.select.app.http;

import java.util.ArrayList;

import rando.randomness.app.demo.model.Report;

public class CreateReportResponse {
	public final ArrayList<Report> reports;
	public final int httpCode;
	public final String error;
	
	
	public CreateReportResponse(ArrayList<Report> r) {
		this.reports = r;
		this.httpCode = 200;
		this.error = "";
	}
	
	public CreateReportResponse(int code, String err) {
		this.reports = new ArrayList<Report>();
		this.httpCode = code;
		this.error = err;
	}
	
	public String toString() {
		if(httpCode == 200) {
			return "ReportResponse(" + reports.toString() + ")";
		}
		else return "ReportResponseError("+ this.httpCode + ", " + this.error + ")";
	}
}
