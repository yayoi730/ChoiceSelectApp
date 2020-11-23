package rando.randomness.app.demo.model;

import java.util.ArrayList;

public class Alternative {
	String aID;
	ArrayList<Member> approvers;
	ArrayList<Member> disapprovers;
	ArrayList<Feedback> feedback;
	String description;
	public Alternative(String description) {
		this.description = description;
	}
	public String getDescription() {return this.description;}
	
	public String getAID() {return this.aID;}
	public void setAID(String aID) {this.aID = aID;}
	

}
