package rando.randomness.app.demo.model;

import java.util.ArrayList;

public class Alternative {
	String aID;
	ArrayList<Member> approvers = new ArrayList<>();
	ArrayList<Member> disapprovers = new ArrayList<>();
	ArrayList<Feedback> feedback = new ArrayList<>();
	String description;
	public Alternative(String description) {
		this.description = description;
	}
	public String getDescription() {return this.description;}
	
	public int getNumApprovers(ArrayList<Member> approvers) {
		int i = 0;
		for(Member m : approvers) {
			i++;
		}
		return i;
	}
	public void addApprover(Member m) {
		this.approvers.add(m);
	}
	public void removeApprover(String name) {
		for(Member m : approvers) {
			if(m.getName() == name) {
				this.approvers.remove(m);
			}
		}
	}
	public ArrayList<Member> getApprovers(){
		return this.approvers;
	}
	public void addDisapprover(Member m) {
		this.disapprovers.add(m);
	}
	public void removeDisapprover(String name) {
		for(Member m : approvers) {
			if(m.getName() == name) {
				this.disapprovers.remove(m);
			}
		}
	}
	public ArrayList<Member> getDispprovers(){
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
