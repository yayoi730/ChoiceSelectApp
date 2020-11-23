package rando.randomness.app.demo.model;

import java.util.ArrayList;

public class Alternative {
<<<<<<< Updated upstream
	String aID;
	ArrayList<Member> approvers;
	ArrayList<Member> disapprovers;
=======
	ArrayList<String> approvers;
	ArrayList<String> disapprovers;
>>>>>>> Stashed changes
	ArrayList<Feedback> feedback;
	String description;
	
	public Alternative(String description) {
		this.description = description;
		this.approvers = new ArrayList<String>();
		this.disapprovers =  new ArrayList<String>();
		this.feedback =  new ArrayList<Feedback>();
	}
	
	public Alternative(String description, ArrayList<String> a, ArrayList<String> d, ArrayList<Feedback> f) {
		this.description = description;
		this.approvers = a;
		this.disapprovers = d;
		this.feedback = f;
	}
	public String getDescription() {return this.description;}
	
<<<<<<< Updated upstream
	public String getAID() {return this.aID;}
	public void setAID(String aID) {this.aID = aID;}
	

=======
	public ArrayList<String> getApprovers() {
		return this.approvers;
	}
	public ArrayList<String> getDisapprovers() {
		return this.disapprovers;
	}
	public ArrayList<Feedback> getFeedback() {
		return this.feedback;
	}
>>>>>>> Stashed changes
}
