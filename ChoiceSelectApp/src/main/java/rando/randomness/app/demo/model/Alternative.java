
package rando.randomness.app.demo.model;

import java.util.ArrayList;

public class Alternative {
	String aID;
	ArrayList<String> approvers = new ArrayList<>();
	ArrayList<String> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	String description;
	
	public Alternative(String description) {
		this.description = description;
	}
	
	public String getDescription() {return this.description;}
	
	public int getNumApprovers(ArrayList<String> approvers) {
		int i = 0;
		for(String s : approvers) {
			i++;
		}
		return i;
	}
	public void addApprover(String s) {
		this.approvers.add(s);
	}
	public void removeApprover(String name) {
		for(String s : approvers) {
			if(s == name) {
				this.approvers.remove(s);
			}
		}
	}
	public ArrayList<String> getApprovers(){
		return this.approvers;
	}
	public void addDisapprover(String s) {
		this.disapprovers.add(s);
	}
	public void removeDisapprover(String name) {
		for(String s : approvers) {
			if(s == name) {
				this.disapprovers.remove(s);
			}
		}
	}
	public ArrayList<String> getDispprovers(){
		return this.disapprovers;
	}
	public void addFeedback(Feedback f) {
		feedback.add(f);
	}
	public ArrayList<Feedback> getFeebackList(){
		return this.feedback;
	}
	public String getAID() {return this.aID;}
	public void setAID(String aID) {this.aID = aID;}

}
