package choice.select.app.http;

import java.util.ArrayList;

public class OpinionRequest {
	String aID;
	String approver;
	boolean approves;
	
	public OpinionRequest() {}
	public OpinionRequest(String prover, boolean approves, String aID ) {
		this.approver = approver;
		this.approves = approves;
		this.aID = aID;
	}
	public String getaID() {return aID;}
	public void setaID(String aID) {this.aID = aID;}
	
	public String getProver() {return approver;}
	public void setProver(String prover) {this.approver = prover;}
	
	public Boolean getApproves() {return approves;}
	public void setApproves(Boolean approves) {this.approves = approves;}

}
