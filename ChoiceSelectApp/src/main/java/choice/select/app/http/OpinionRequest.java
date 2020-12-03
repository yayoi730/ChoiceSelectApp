package choice.select.app.http;

import java.util.ArrayList;

public class OpinionRequest {
	ArrayList<String> approvers;
	ArrayList<String> disapprovers;
	String aID;
	String prover;
	boolean approves;
	
	public OpinionRequest() {}
	public OpinionRequest(String prover, boolean approves, String aID ) {
		this.prover = prover;
		this.approves = approves;
		this.aID = aID;
	}
	public String getaID() {return aID;}
	public void setaID(String aID) {this.aID = aID;}
	
	public String getProver() {return prover;}
	public void setProver(String prover) {this.prover = prover;}
	
	public Boolean getApproves() {return approves;}
	public void setApproves(Boolean approves) {this.approves = approves;}

}
