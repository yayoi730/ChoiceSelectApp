package rando.randomness.app.demo.model;

import java.util.ArrayList;


public class Team {
	
	String tID;
	ArrayList<Member> members = new ArrayList<>();
	Choice choice;
	int teamSize;
	
	public int getTeamSize() {return teamSize;}
	public void setTeamSize(int s) {this.teamSize = s;}
	
	public Team(ArrayList<Member> members, Choice choice) {
		this.members = members;
		this.choice = choice;
	}
	public Team (Member member, Choice choice, int teamSize) {
		ArrayList<Member> mem = new ArrayList<Member>();
		mem.add(member);
		this.members = mem;
		this.choice = choice;
		this.teamSize = teamSize;
	}
	
	public void addMember(String username, String password) {
		members.add(new Member(username,password));
	}
	
	public Choice getChoice(){return this.choice;}
	
	public void addMember(String username) { members.add(new Member(username));}
	
	public Team createTeam(ArrayList<Member> members, Choice choice ) {
		Team t = new Team(members, choice);
		return t;
	}
	
	public ArrayList<Member> getMembers(){
		return this.members;
	}
	
	public String getTID() {return this.tID;}
	public void setTID(String tID) {this.tID = tID;}
	

}