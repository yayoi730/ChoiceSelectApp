package rando.randomness.app.demo.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Team implements Iterable<Member>{
	String tID;
	ArrayList<Member> members;
	Choice choice;
	public Team(ArrayList<Member> members,Choice choice) {
		this.members = members;
		this.choice = choice;
	}
	public Iterator<Member> iterator() {
		return members.iterator();
	}
	

	public String getTID() {return this.tID;}
	public void setTID(String tID) {this.tID = tID;}

}
