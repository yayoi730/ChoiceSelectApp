package rando.randomness.app.demo.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Team {
	String tID;
	ArrayList<Member> members = new ArrayList<>();
	Choice choice;
	public Team(Member m,Choice choice) {
		this.members = m;
		this.choice = choice;
	}
	public void addMember(String username, String password) {
		members.add(new Member(username,password));
	}
	public void addMember(String username) {
		members.add(new Member(username));
	}
	public void createTeam(ArrayList<Member> members, Choice choice ) {
		Team t = new Team(members, choice);
	}
	public ArrayList<Member> getMembers(){
		return this.members;
	}
	public String getTID() {return this.tID;}
	public void setTID(String tID) {this.tID = tID;}

}